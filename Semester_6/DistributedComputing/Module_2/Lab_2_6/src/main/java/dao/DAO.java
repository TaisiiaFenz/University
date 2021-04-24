package dao;

import java.util.List;

public interface DAO<T> extends AutoCloseable {

    List<T> findAll();

    T save(T item);

    T findById(int id);

    boolean deleteById(int id);
}