package lotto.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LottoRoundDAO {
    private final Connection connection;

    public LottoRoundDAO(Connection connection) {
        this.connection = connection;
    }

    public int totalRound() throws SQLException {
        String query = "SELECT count(*) FROM ROUND";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();

        if (!resultSet.next()) return 0;

        return resultSet.getInt(1);
    }

    public void playRound() throws SQLException {
        String query = "INSERT INTO ROUND VALUES(?)";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, 0);
        preparedStatement.executeUpdate();
    }

    public List<Integer> inquireTotalRound() throws SQLException {
        String query = "SELECT * FROM ROUND";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();

        List<Integer> rounds = new ArrayList<>();

        while (resultSet.next()) {
            rounds.add(resultSet.getInt("id"));
        }

        return rounds;
    }
}

