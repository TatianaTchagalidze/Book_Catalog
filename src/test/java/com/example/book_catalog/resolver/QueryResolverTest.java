package com.example.book_catalog.resolver;

import com.example.book_catalog.entity.Book;
import com.example.book_catalog.repository.BookCatalogRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class QueryResolverTest {

  @Mock
  private BookCatalogRepository bookCatalogRepository;

  @InjectMocks
  private QueryResolver queryResolver;

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void testGetBookById() {
    // Given
    int bookId = 1;
    Book expectedBook = new Book("Sample Book", "Sample Author", "Sample Genre", 2023);
    expectedBook.setId(bookId);

    when(bookCatalogRepository.findById(bookId)).thenReturn(expectedBook);

    // When
    Book result = queryResolver.book(bookId);

    // Then
    assertEquals(expectedBook, result);
    verify(bookCatalogRepository, times(1)).findById(bookId);

    // Print the results
    System.out.println("Expected Book by id: " + expectedBook);
    System.out.println("Actual Book by id : " + result);
  }

  @Test
  public void testGetAllBooks() {
    // Given
    List<Book> expectedBooks = new ArrayList<>();
    expectedBooks.add(new Book("Book 1", "Author 1", "Genre 1", 2000));
    expectedBooks.add(new Book("Book 2", "Author 2", "Genre 2", 2010));
    expectedBooks.add(new Book("Book 3", "Author 3", "Genre 3", 2020));

    when(bookCatalogRepository.findAll()).thenReturn(expectedBooks);

    // When
    List<Book> result = queryResolver.allBooks();

    // Then
    assertEquals(expectedBooks, result);
    verify(bookCatalogRepository, times(1)).findAll();

    // Print the results
    System.out.println("Expected all Books: " + expectedBooks);
    System.out.println("Actual all Books  : " + result);
  }
}
