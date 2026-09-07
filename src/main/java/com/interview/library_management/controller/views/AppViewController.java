package com.interview.library_management.controller.views;

import com.interview.library_management.dto.BookDto;
import com.interview.library_management.service.BookService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/")
public class AppViewController {

    private final BookService bookService;

    @GetMapping
    public String homePage(){
        return "homepage";
    }

    @GetMapping("/books")
    public String getAllBooks(Model model){
        List<BookDto> books = bookService.getAll();
        model.addAttribute("books", books);
        return "book/all_books";
    }

    @GetMapping("/books/add")
    public String newBook(Model model){
        return "book/add_book";
    }

    @PostMapping("/books/add")
    public String addBook(
            @Valid @ModelAttribute BookDto bookDto,
            BindingResult bindingResult,
            Model model
            ){
        if(bindingResult.hasErrors()){
            model.addAttribute("errors",bindingResult.getAllErrors());
        }
        bookService.add(bookDto);
        return "redirect:/books";
    }

    @GetMapping("/books/update")
    public String updateBook(Model model){
        return "book/update_book";
    }

    @PostMapping("books/update")
    public String updateBook(
            @Valid @ModelAttribute BookDto bookDto,
            BindingResult bindingResult,
            Model model
    ){
        if(bindingResult.hasErrors()){
            model.addAttribute("errors",bindingResult.getAllErrors());
        }
        bookService.update(bookDto);
        model.addAttribute("successfullyUpdate",true);
        model.addAttribute("bookDto",bookDto);
        return "redirect:/books";
    }

    @GetMapping("/books/{id}")
    public String getBookById(@PathVariable Long id, Model model){
        BookDto bookDto = bookService.getById(id);
        model.addAttribute("bookDto",bookDto);
        return "book/individual_book";
    }

}
