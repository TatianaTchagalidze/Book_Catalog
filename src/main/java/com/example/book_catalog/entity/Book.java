package com.example.book_catalog.entity;

import java.util.Objects;

public class Book {
  private Integer id;
  private String title;
  private String author;
  private String genre;
  private int publishedYear;

  // Constructor
  public Book() {
  }
  public Book(String title, String author, String genre, int publishedYear) {
    this.title = title;
    this.author = author;
    this.genre = genre;
    this.publishedYear = publishedYear;
  }


  // Getters and Setters
  public Integer getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getAuthor() {
    return author;
  }

  public void setAuthor(String author) {
    this.author = author;
  }

  public String getGenre() {
    return genre;
  }

  public void setGenre(String genre) {
    this.genre = genre;
  }

  public int getPublishedYear() {
    return publishedYear;
  }

  public void setPublishedYear(int publishedYear) {
    this.publishedYear = publishedYear;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Book book = (Book) o;
    return publishedYear == book.publishedYear &&
        Objects.equals(id, book.id) &&
        Objects.equals(title, book.title) &&
        Objects.equals(author, book.author) &&
        Objects.equals(genre, book.genre);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, title, author, genre, publishedYear);
  }

  @Override
  public String toString() {
    return "Book{" +
        "id=" + id +
        ", title='" + title + '\'' +
        ", author='" + author + '\'' +
        ", genre='" + genre + '\'' +
        ", publishedYear=" + publishedYear +
        '}';
  }
}

