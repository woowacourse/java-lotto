package lotto.dao.utils;

import lotto.dao.exception.DataAccessException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLDataException;
import java.sql.SQLException;

import static lotto.dao.utils.JdbcConnector.getConnection;

@FunctionalInterface
public interface DaoTemplate {
    /**
     * General CUD(Create, Update, Delete) template
     *
     * @param query Query to execute
     * @return result Query result
     * @throws SQLDataException
     */
    default int cudTemplate(String query) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            setPreparedStatement(preparedStatement);

            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DataAccessException(e);
        }
    }

    void setPreparedStatement(PreparedStatement preparedStatement) throws SQLException;
}
