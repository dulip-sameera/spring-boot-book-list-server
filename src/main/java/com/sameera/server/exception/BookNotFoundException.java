package com.sameera.server.exception;

public class BookNotFoundException extends RuntimeException{

    public BookNotFoundException(Integer id) {
        super("Could not found the book with id "+ id);
    }
}
