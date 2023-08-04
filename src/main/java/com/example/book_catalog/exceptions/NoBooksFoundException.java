package com.example.book_catalog.exceptions;

public class NoBooksFoundException extends RuntimeException {
  public NoBooksFoundException(String message) {
    super(message);
  }
}
