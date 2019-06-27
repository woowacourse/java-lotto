package lotto.dao;

import lotto.domain.lotto.Lotto;
import lotto.domain.lotto.Numbers;
import lotto.domain.result.Winning;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LottoYieldDAO {
    private Connection connection;

    public LottoYieldDAO(Connection connection) {
        this.connection = connection;
    }

    public void saveYield(BigDecimal yield, int round) throws SQLException {
        String query = "INSERT INTO YIELD VALUES(0, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(query);

        preparedStatement.setBigDecimal(1, yield);
        preparedStatement.setInt(2, round);
        preparedStatement.executeUpdate();
    }

    public BigDecimal inquireYield(int round) throws SQLException {
        String query = "SELECT lotto_yield FROM YIELD WHERE round_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, round);

        ResultSet resultSet = preparedStatement.executeQuery();

        if (!resultSet.next()) return null;

        return resultSet.getBigDecimal("lotto_yield");
    }
}
