package lotto.dao.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLDataException;
import java.sql.SQLException;

import static lotto.dao.utils.JdbcConnector.getConnection;

@FunctionalInterface
public interface DaoTemplate {
    /**
     * General CUD(Create, Update, Delete) template
     * @param query Query to execute
     * @return result Query result
     * @throws SQLDataException
     */
    default int cudTemplate(String query) throws SQLDataException {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            setPreparedStatement(preparedStatement);

            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLDataException();
        }
    }

    void setPreparedStatement(PreparedStatement preparedStatement) throws SQLException;
}
