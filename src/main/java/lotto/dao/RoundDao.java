package lotto.dao;

import lotto.domain.LottoMoney;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RoundDao {
    private Connection connection;

    public RoundDao(final Connection connection) {
        this.connection = connection;
    }

    public void addRound(int round, LottoMoney lottoMoney) throws SQLException {
        //Todo 아래와 같이 하면 왜 안되는지 알아보기
        //String query = "INSERT INTO game VALUES(?)";
        String query = "INSERT INTO game(money, round) VALUES(?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, Integer.parseInt(lottoMoney.toString()));
        preparedStatement.setInt(2, round);
        preparedStatement.executeUpdate();
    }

    public int findMoneyByRound(int round) throws SQLException {
        String query = "SELECT money FROM game where round=?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, round);
        ResultSet resultSet = preparedStatement.executeQuery();
        int money = -1;
        while (resultSet.next()) {
            money = resultSet.getInt("money");
        }
        return money;
    }

    public int findMaxRound() throws SQLException {
        String query = "SELECT MAX(round) FROM game";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            return resultSet.getInt("MAX(round)");
        }
        return 0;
    }

    public void deleteMoneyByRound(int round) throws SQLException {
        String query = "DELETE FROM game WHERE round = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, round);
        preparedStatement.executeUpdate();
    }

}
