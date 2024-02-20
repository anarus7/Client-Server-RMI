package ro.ubb.lab3.server.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcOperations;
import ro.ubb.lab3.common.Purchase;

import java.util.Optional;

public class PurchaseDatabaseRepository implements Repository<Long, Purchase> {
    @Autowired
    private JdbcOperations jdbcOperations;

    @Override
    public Optional<Purchase> findOne(Long id) {
        String query = "SELECT * FROM purchase WHERE ID = ?";
        return Optional.ofNullable(jdbcOperations.queryForObject(query, new BeanPropertyRowMapper<Purchase>(Purchase.class), id));
    }

    @Override
    public Iterable<Purchase> findAll() {
        String query = ("SELECT * FROM purchase");
        return jdbcOperations.query(query, new BeanPropertyRowMapper<Purchase>(Purchase.class));
    }

    @Override
    public Optional<Purchase> save(Purchase entity) {
        jdbcOperations.update("INSERT INTO purchase (id,bookId,clientId, numberSold, DateOfPurchase) VALUES (?,?,?,?,?)",
                entity.getId(), entity.getBookId(), entity.getClientId(), entity.getNumberSold(), entity.getDateOfPurchase());
        return Optional.of(entity);
    }

    @Override
    public Optional<Purchase> delete(Long aLong) {
        Optional<Purchase> purchase = findOne(aLong);
        String sql = "delete from purchase where (id=?)";
        int deletedRows = jdbcOperations.update(sql, aLong);
        return Optional.empty();
    }

    @Override
    public Optional<Purchase> update(Purchase entity) {
        String sql = "update purchase set bookId=?,  clientId=?,  numberSold=?,  dateOfPurchase=? where id=?";
        int updatedRows = jdbcOperations.update(sql, entity.getBookId(), entity.getClientId(), entity.getNumberSold(),
                entity.getDateOfPurchase(), entity.getId());
        return Optional.empty();

    }

}


