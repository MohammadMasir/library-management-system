package com.interview.library_management.controller.rest;

import com.interview.library_management.dto.BookDto;
import com.interview.library_management.service.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/books")
public class BookController {

    private final BookService bookService;

    @GetMapping
    public ResponseEntity<List<BookDto>> getAllBooks(){
        List<BookDto> books = bookService.getAll();
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Void> addBook(@RequestBody @Valid BookDto bookDto){
        bookService.add(bookDto);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateBook(@RequestBody @Valid BookDto bookDto, @PathVariable Long id){
//        bookService.update(id, bookDto);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDto> getBookById(@PathVariable Long id){
        BookDto book = bookService.getById(id);
        return new ResponseEntity<>(book, HttpStatus.OK);
    }

}
