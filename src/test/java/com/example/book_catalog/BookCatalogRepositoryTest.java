package com.example.book_catalog;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import com.example.book_catalog.entity.Book;
import com.example.book_catalog.repository.BookCatalogRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class BookCatalogRepositoryTest {

  @Mock
  private JdbcTemplate jdbcTemplate;

  @InjectMocks
  private BookCatalogRepository bookCatalogRepository;

  private List<Book> mockBooks;

  private static final Logger LOGGER = LoggerFactory.getLogger(BookCatalogRepositoryTest.class);

  @Before
  public void setUp() {
    // Create some mock Book data for testing
    mockBooks = new ArrayList<>();
    mockBooks.add(createBook(1, "Book 1", "Author 1", "Genre 1", 2021));
    mockBooks.add(createBook(2, "Book 2", "Author 2", "Genre 2", 2022));
    mockBooks.add(createBook(3, "Book 3", "Author 3", "Genre 3", 2023));
  }

  private Book createBook(int id, String title, String author, String genre, int publishedYear) {
    Book book = new Book();
    book.setId(id);
    book.setTitle(title);
    book.setAuthor(author);
    book.setGenre(genre);
    book.setPublishedYear(publishedYear);
    return book;
  }

  @Test
  public void testFindAll() {
    when(jdbcTemplate.query(anyString(), any(RowMapper.class))).thenReturn(mockBooks);

    List<Book> result = bookCatalogRepository.findAll();

    // Verify that the correct list of books is returned
    assertEquals(mockBooks.size(), result.size());
    assertEquals(mockBooks, result);

    System.out.println("Test FindAll - Found " + result.size() + " books");
    for (Book book : result) {
      System.out.println("Test FindAll - Book: " + book);
    }
  }
}

