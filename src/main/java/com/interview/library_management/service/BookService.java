package com.interview.library_management.service;

import com.interview.library_management.dto.BookDto;
import com.interview.library_management.exceptions.BookExistsException;
import com.interview.library_management.exceptions.BookNotFoundException;
import com.interview.library_management.exceptions.ResourceNotFound;
import com.interview.library_management.model.Book;
import com.interview.library_management.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class BookService {

    private final BookRepository bookRepository;

    public List<BookDto> getAll() {

        // Persistence / DB Logic
        List<Book> books = bookRepository.findAll();

        return books.stream()
                .map(book -> new BookDto(
                        book.getId(),
                        book.getTitle(),
                        book.getAuthor(),
                        book.getDescription()
                ))
                .sorted(Comparator.comparing(BookDto::id))
                .toList();
    }

    @Transactional
    public void add(BookDto bookDto) {

        // Conflict handling
        if (bookRepository.findBookByTitleAndAuthorIgnoreCase(bookDto.title(), bookDto.authorName()).isPresent()) {
//            model.addAttribute("error", )
            throw new BookExistsException(
                    "Book already present in Library."
            );
        }

        // Persistence/Database logic
        Book book = new Book();
        book.setAuthor(bookDto.authorName());
        book.setTitle(bookDto.title());
        book.setDescription(bookDto.description());
        bookRepository.save(book);

    }

    @Transactional
    public void update(BookDto bookDto) {
        Book book = bookRepository.findById(bookDto.id())
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Book not Present in Library!"
                        )
                        );
//        Book book = bookRepository.findById(bookDto.id()).orElseThrow(
//                () -> new BookNotFoundException("Book not found")
//        );
        book.setAuthor(bookDto.authorName());
        book.setTitle(bookDto.title());
        book.setDescription(bookDto.description());
        bookRepository.save(book);
    }

    public BookDto getById(Long id) {
        Book book = bookRepository.findById(id).orElseThrow(
                () -> new ResourceNotFound("Book not found")
        );
        return new BookDto(
                book.getTitle(),
                book.getAuthor(),
                book.getDescription()
        );
    }
}
