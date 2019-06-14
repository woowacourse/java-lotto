package lotto.domain.lotto.db;

import java.sql.SQLException;

public interface DBHandler<T> {
    void add(T t) throws SQLException;

    T foundById(int id) throws SQLException;
}
