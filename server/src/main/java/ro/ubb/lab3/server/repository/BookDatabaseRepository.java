package ro.ubb.lab3.server.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcOperations;
import ro.ubb.lab3.common.Book;

import java.util.*;

public class BookDatabaseRepository implements Repository<Long, Book> {
    @Autowired
    private JdbcOperations jdbcOperations;



    @Override
    public Optional<Book> findOne(Long id) {
        String query = "SELECT * FROM book WHERE ID = ?";
        return Optional.ofNullable(jdbcOperations.queryForObject(query, new BeanPropertyRowMapper<Book>(Book.class), id));
    }

    @Override
    public Iterable<Book> findAll() {
        String query = ("SELECT * FROM book");
        return jdbcOperations.query(query, new BeanPropertyRowMapper<Book>(Book.class));
    }

    @Override
    public Optional<Book> save(Book entity) {
        jdbcOperations.update("INSERT INTO book (id,title,author,publisher,price,stock) VALUES (?,?,?,?,?,?)",
                entity.getId(), entity.getTitle(), entity.getAuthor(), entity.getPublisher(), entity.getPrice(), entity.getStock());
        return Optional.of(entity);
    }

    @Override
    public Optional<Book> delete(Long aLong) {
        jdbcOperations.update("DELETE FROM book WHERE id=?", aLong);
        return Optional.empty();
    }

    @Override
    public Optional<Book> update(Book entity) {
        String sql = "UPDATE book SET title=?, author=?, publisher=?, price=?, stock=? WHERE id=?";
        jdbcOperations.update(sql, entity.getTitle(), entity.getAuthor(), entity.getPublisher(),
                entity.getPrice(), entity.getStock(), entity.getId());
        return Optional.empty();

    }

}

