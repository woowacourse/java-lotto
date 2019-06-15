package lotto.model.dao;

//import com.mysql.cj.x.protobuf.MysqlxDatatypes;

import lotto.model.LottoResult;
import lotto.model.Money;
import lotto.model.config.DBConnector;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LottoResultDao {
    public void saveResult(List<String> result, Money money, LottoResult lottoResult) throws SQLException {
        int round = getLatestRound();
        String query = "INSERT INTO lotto_result_info (round, result, profit_rate, prize_money) VALUES(?,?,?,?)";
        Connection connection = DBConnector.getConnection();
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setInt(1, round);
        ps.setString(2, String.join("\n", result));
        ps.setString(3, "" + lottoResult.calculateProfitRate());
        ps.setString(4, "" + lottoResult.calculateTotalPrizeMoney());
        ps.executeUpdate();
        DBConnector.closeConnection(connection);
    }

    private int getLatestRound() throws SQLException {
        String query = "SELECT MAX(ROUND) as MAX_ROUND FROM lotto_result_info";
        Connection connection = DBConnector.getConnection();
        PreparedStatement ps = connection.prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        int round = 77;
        if (rs.next()) {
            round = rs.getInt("MAX_ROUND");
        }
        DBConnector.closeConnection(connection);
        return round + 1;
    }

    public void fetchRequestResult(int requestRound, List<String> results) throws SQLException {
        String query = "SELECT * FROM lotto_result_info WHERE round = ?";
        Connection connection = DBConnector.getConnection();
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setInt(1, requestRound);
        ResultSet rs = ps.executeQuery();

        List<String> r = new ArrayList<>();
        if (rs.next()) {
            results.add(rs.getString("result"));
            results.add("당첨금액: \n");
            results.add(rs.getInt("prize_money") + "");
            results.add("수익률: \n");
            results.add(rs.getInt("profit_rate") + "%");
        }
        DBConnector.closeConnection(connection);
    }
}

