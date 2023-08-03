package com.example.book_catalog.resolver;

import com.example.book_catalog.entity.Book;
import com.example.book_catalog.repository.BookCatalogRepository;
import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class QueryResolver implements GraphQLQueryResolver {
  private final BookCatalogRepository bookCatalogRepository;

  @Autowired
  public QueryResolver(BookCatalogRepository bookCatalogRepository) {
    this.bookCatalogRepository = bookCatalogRepository;
  }

  public Book book(Integer id) {
    return bookCatalogRepository.findById(id);
  }

  public List<Book> allBooks() {
    return bookCatalogRepository.findAll();
  }
}

