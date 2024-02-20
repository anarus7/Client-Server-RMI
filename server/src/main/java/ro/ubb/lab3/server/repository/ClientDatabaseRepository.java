package ro.ubb.lab3.server.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcOperations;
import ro.ubb.lab3.common.Client;

import java.util.Optional;

public class ClientDatabaseRepository implements Repository<Long, Client> {
    @Autowired
    private JdbcOperations jdbcOperations;



    @Override
    public Optional<Client> findOne(Long id) {
        String query = "SELECT * FROM client WHERE ID = ?";
        return Optional.ofNullable(jdbcOperations.queryForObject(query, new BeanPropertyRowMapper<Client>(Client.class), id));
    }

    @Override
    public Iterable<Client> findAll() {
        String query = ("SELECT * FROM client");
        return jdbcOperations.query(query, new BeanPropertyRowMapper<Client>(Client.class));
    }

    @Override
    public Optional<Client> save(Client entity) {
        jdbcOperations.update("INSERT INTO client (id,firstname,lastname,phonenumber) VALUES (?,?,?,?)",
                entity.getId(), entity.getFirstName(), entity.getLastName(), entity.getPhoneNumber());
        return Optional.of(entity);
    }

    @Override
    public Optional<Client> delete(Long aLong) {
        jdbcOperations.update("DELETE FROM client WHERE id=?", aLong);
        return Optional.empty();
    }

    @Override
    public Optional<Client> update(Client entity) {
        String sql = "UPDATE client SET firstname=?, lastname=?, phonenumber=? WHERE id=?";
        jdbcOperations.update(sql, entity.getFirstName(), entity.getLastName(), entity.getPhoneNumber(),
                entity.getId());
        return Optional.empty();

    }
}
