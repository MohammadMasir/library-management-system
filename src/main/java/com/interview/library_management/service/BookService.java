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
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class BookService {

    private final BookRepository bookRepository;

    public List<BookDto> getAll() {
        List<Book> books = bookRepository.findAll();

        return books.stream()
                .map(book -> new BookDto(
                        book.getTitle(),
                        book.getAuthor(),
                        book.getDescription()
                ))
                .toList();
    }

    @Transactional
    public void add(BookDto bookDto) {
        if (bookRepository.findBookByTitleAndAuthor(bookDto.authorName(), bookDto.title()).isPresent()) {
            throw new BookExistsException(
                    "Book already present in Library."
            );
        }
        Book book = new Book();
        book.setAuthor(bookDto.authorName());
        book.setTitle(bookDto.title());
        book.setDescription(bookDto.description());
        bookRepository.save(book);

    }

    @Transactional
    public void update(BookDto bookDto) {
        Book book = bookRepository.findById(bookDto.id()).orElseThrow(
                () -> new BookNotFoundException("Book not found")
        );
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
