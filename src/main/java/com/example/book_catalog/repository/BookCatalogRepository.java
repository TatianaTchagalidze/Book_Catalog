package com.example.book_catalog.repository;

import com.example.book_catalog.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class BookCatalogRepository {

  private final JdbcTemplate jdbcTemplate;

  @Autowired
  public BookCatalogRepository(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  public Book findById(Integer id) {
    String sql = "SELECT * FROM Book WHERE id = ?";
    return jdbcTemplate.queryForObject(sql, new Object[]{id}, new BookRowMapper());
  }


  public List<Book> findAll() {
    String sql = "SELECT * FROM Book";
    return jdbcTemplate.query(sql, new BookRowMapper());
  }

  public void save(Book book) {
    String sql = "INSERT INTO Book (title, author, genre, publishedYear) VALUES (?, ?, ?, ?)";
    jdbcTemplate.update(sql, book.getTitle(), book.getAuthor(), book.getGenre(), book.getPublishedYear());
  }

  public void update(Book book) {
    String sql = "UPDATE Book SET title = ?, author = ?, genre = ?, publishedYear = ? WHERE id = ?";
    jdbcTemplate.update(sql, book.getTitle(), book.getAuthor(), book.getGenre(), book.getPublishedYear(), book.getId());
  }

  // RowMapper for mapping database rows to Book objects
  private static class BookRowMapper implements RowMapper<Book> {
    @Override
    public Book mapRow(ResultSet resultSet, int rowNum) throws SQLException {
      Book book = new Book();
      book.setId(resultSet.getInt("id"));
      book.setTitle(resultSet.getString("title"));
      book.setAuthor(resultSet.getString("author"));
      book.setGenre(resultSet.getString("genre"));
      book.setPublishedYear(resultSet.getInt("publishedYear"));

      // Log the retrieved values
      System.out.println("BookRowMapper - ID: " + book.getId() + ", Title: " + book.getTitle() + ", Author: " + book.getAuthor() + ", Genre: " + book.getGenre() + ", PublishedYear: " + book.getPublishedYear());

      return book;
    }
  }

}

