package lotto.dao;

import lotto.domain.lotto.Lotto;
import lotto.domain.lotto.Numbers;
import lotto.domain.result.Winning;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LottoWinningDAO {
    private final Connection connection;

    public LottoWinningDAO(Connection connection) {
        this.connection = connection;
    }

    public void saveWinning(String winning, int bonusNumber, int round) throws SQLException {
        String query = "INSERT INTO WINNING VALUES(0, ?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(query);

        preparedStatement.setString(1, winning);
        preparedStatement.setInt(2, bonusNumber);
        preparedStatement.setInt(3, round);
        preparedStatement.executeUpdate();
    }

    public Winning inquireWinning(int round) throws SQLException {
        String query = "SELECT lotto_number, bonus_number FROM WINNING WHERE round_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, round);

        ResultSet resultSet = preparedStatement.executeQuery();
        if (!resultSet.next()) return null;

        Lotto lotto = Lotto.of(new Numbers(resultSet.getString("lotto_number")));
        int bonusNumber = Integer.valueOf(resultSet.getString("bonus_number"));

        return Winning.of(lotto, bonusNumber);
    }
}
