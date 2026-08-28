package com.interview.library_management.repository;

import com.interview.library_management.model.Book;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {

    Optional<Book> findBookByTitleAndAuthor(@NotNull String author, @NotNull String title);
}
