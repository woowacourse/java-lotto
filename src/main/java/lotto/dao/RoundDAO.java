package lotto.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoundDAO {
    private RoundDAO() {}

    private static class RoundDAOHolder {
        static final RoundDAO ROUND_DAO = new RoundDAO();
    }

    public static RoundDAO getInstance() {
        return RoundDAOHolder.ROUND_DAO;
    }

    public int findMaxRound() {
        String sql = "SELECT MAX(round) AS max FROM round";
        int result = 0;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            if (!resultSet.next()) {
                return 1;
            }
            result = resultSet.getInt("max");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public int findAmountByRound(int round) {
        String sql = "SELECT amount FROM round WHERE round = ?";
        int result = 0;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, round);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (!resultSet.next()) {
                    return 0;
                }
                result = resultSet.getInt("amount");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public List<Integer> findAllRounds() {
        String sql = "SELECT round FROM round ORDER BY round DESC";
        List<Integer> rounds = new ArrayList<>();

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                rounds.add(resultSet.getInt("round"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rounds;
    }

    public void addRound(int amount) {
        String sql = "INSERT INTO round(amount) VALUES (?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, amount);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeRound(int round) {
        String sql = "DELETE FROM round WHERE round = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, round);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
