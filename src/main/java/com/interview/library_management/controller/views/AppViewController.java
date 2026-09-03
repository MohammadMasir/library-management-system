package com.interview.library_management.controller.views;

import com.interview.library_management.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@Controller
@RequestMapping("/")
public class AppViewController {

    private final BookService bookService;

    @GetMapping
    public String homePage(){
        return "homepage";
    }


}
