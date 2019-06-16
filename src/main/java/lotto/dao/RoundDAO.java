package lotto.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RoundDAO extends DAO {
    public String getRound() throws SQLException {
        String query = "select IFNULL(MAX(round), 0) AS max_round FROM winningLotto";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        return String.valueOf(resultSet.getInt("max_round") + 1);
    }
}
