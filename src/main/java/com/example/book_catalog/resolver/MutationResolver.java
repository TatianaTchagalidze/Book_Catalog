package com.example.book_catalog.resolver;

import com.example.book_catalog.entity.Book;
import com.example.book_catalog.repository.BookCatalogRepository;
import graphql.kickstart.tools.GraphQLMutationResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MutationResolver implements GraphQLMutationResolver {

  private final BookCatalogRepository bookCatalogRepository;

  @Autowired
  public MutationResolver(BookCatalogRepository bookCatalogRepository) {
    this.bookCatalogRepository = bookCatalogRepository;
  }

  public Book addBook(String title, String author, String genre, int publishedYear) {
    Book newBook = new Book(title, author, genre, publishedYear);
    bookCatalogRepository.save(newBook);
    return newBook;
  }

  public Book updateBook(int id, String title, String author, String genre, int publishedYear) {
    Book existingBook = bookCatalogRepository.findById(id);
    if (existingBook != null) {
      existingBook.setTitle(title);
      existingBook.setAuthor(author);
      existingBook.setGenre(genre);
      existingBook.setPublishedYear(publishedYear);
      bookCatalogRepository.update(existingBook);
      return existingBook;
    }
    return null;
  }
}
