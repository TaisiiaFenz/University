package ua.taisiia.model.dao;
import ua.taisiia.model.exception.DataExistsException;

import java.util.List;

public interface Dao<T> extends AutoCloseable {
    void create(T entity) throws DataExistsException;

    void update(T entity);

    void delete(Long id);

    T findById(Long id);

    List<T> findAll();
}
