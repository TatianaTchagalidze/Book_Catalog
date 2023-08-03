package com.example.book_catalog.resolver;

import com.example.book_catalog.entity.Book;
import com.example.book_catalog.repository.BookCatalogRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class MutationResolverTest {

  @Mock
  private BookCatalogRepository bookCatalogRepository;

  @InjectMocks
  private MutationResolver mutationResolver;

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void testAddBook() {
    // Given
    String title = "New Book";
    String author = "New Author";
    String genre = "Fiction";
    int publishedYear = 2023;

    Book expectedBook = new Book(title, author, genre, publishedYear);

    // Stub the behavior of the void method using doNothing()
    doNothing().when(bookCatalogRepository).save(any(Book.class));

    // When
    Book result = mutationResolver.addBook(title, author, genre, publishedYear);

    // Then
    assertEquals(expectedBook, result);
    verify(bookCatalogRepository, times(1)).save(any(Book.class));

    // Print the results
    System.out.println("Expected Book: " + expectedBook);
    System.out.println("Actual Book  : " + result);
  }


@Test
  public void testUpdateBook() {
    // Given
    int bookId = 1;
    String title = "Updated Book";
    String author = "Updated Author";
    String genre = "Mystery";
    int publishedYear = 2022;

    Book existingBook = new Book("Old Book", "Old Author", "Old Genre", 2021);
    existingBook.setId(bookId);
    when(bookCatalogRepository.findById(bookId)).thenReturn(existingBook);

    // Mock the bookCatalogRepository.update behavior using doNothing()
    doNothing().when(bookCatalogRepository).update(existingBook);

    // When
    Book result = mutationResolver.updateBook(bookId, title, author, genre, publishedYear);

    // Then
    assertEquals(title, result.getTitle());
    assertEquals(author, result.getAuthor());
    assertEquals(genre, result.getGenre());
    assertEquals(publishedYear, result.getPublishedYear());
    verify(bookCatalogRepository, times(1)).findById(bookId);
    verify(bookCatalogRepository, times(1)).update(existingBook);
  }
}

