package lotto.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RoundDAO {
    public int findMaxRound() throws SQLException {
        String sql = "SELECT MAX(round) AS max FROM round";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            if (!resultSet.next()) {
                return 1;
            }
            return resultSet.getInt("max");
        }
    }

    public int findAmountByRound(int round) throws SQLException {
        String sql = "SELECT amount FROM round WHERE round = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, round);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (!resultSet.next()) {
                    return 0;
                }
                return resultSet.getInt("amount");
            }
        }
    }

    public void addRound(int amount) throws SQLException {
        String sql = "INSERT INTO round(amount) VALUES (?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, amount);
            statement.executeUpdate();
        }
    }

    public void removeRound(int round) throws SQLException {
        String sql = "DELETE FROM round WHERE round = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, round);
            statement.executeUpdate();
        }
    }
}
