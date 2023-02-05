package com.sameera.server.controller;

import com.sameera.server.exception.BookNotFoundException;
import com.sameera.server.model.Book;
import com.sameera.server.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@CrossOrigin("http://localhost:3000")
public class BookController {

    private BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/books/all")
    public ResponseEntity<List<Book>> getAll() {
        return new ResponseEntity<List<Book>>(bookService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/books/{id}")
    public ResponseEntity<Book> get(@PathVariable Integer id) {
        Optional<Book> singleBook = bookService.get(id);
        if (singleBook.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Book>(singleBook.get(), HttpStatus.OK);
    }

    @PostMapping("/books")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Book> addNewBook(@RequestBody Book book) {
        return new ResponseEntity<Book>(bookService.addBook(book), HttpStatus.CREATED);
    }

    @PutMapping("books/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Book> updateBook(@PathVariable Integer id, @RequestBody Book book) {
        return new ResponseEntity<Book>(bookService.updateBook(book,id), HttpStatus.CREATED);
    }

    @DeleteMapping("books/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteBook(@PathVariable Integer id) {
        bookService.deleteBook(id);
    }
}
