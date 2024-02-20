package ro.ubb.lab3.server.repository;


import ro.ubb.lab3.common.BaseEntity;

import java.util.Optional;

public interface Repository<ID, T extends BaseEntity<ID>> {

    Optional<T> findOne(ID id);

    Iterable<T> findAll();

    Optional<T> save(T entity);

    Optional<T> delete(ID id);

    Optional<T> update(T entity);


}
