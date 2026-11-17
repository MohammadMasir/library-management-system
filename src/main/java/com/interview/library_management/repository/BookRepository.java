package com.interview.library_management.repository;

import com.interview.library_management.model.Book;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {
    Optional<Book> findBookByTitleAndAuthorIgnoreCase(String title, String author);

//    Optional<Book> findBookByTitleAndAuthor(@NotNull @Param(value = "author") String author, @NotNull @Param(value = "title") String title);
}
