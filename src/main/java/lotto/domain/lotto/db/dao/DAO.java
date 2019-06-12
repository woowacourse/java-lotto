package lotto.domain.lotto.db.dao;

import java.sql.Connection;
import java.sql.SQLException;

public interface DAO<T> {
    Connection getConnection();

    void closeConnection();

    void add(T t) throws SQLException;

    void create() throws SQLException;

    T foundById(int id) throws SQLException;

    void updateById(int id);

    void delete(int id);
}
