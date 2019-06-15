package lotto.model.dao;

import lotto.model.Lotto;
import lotto.model.Lottos;
import lotto.model.config.DBConnector;

import java.sql.*;
import java.util.List;

public class LottosDao {
    public void saveLottos(Lottos lottos) throws SQLException {
        int round = getLatestRound();
        for (Lotto lotto : lottos.getLottos()) {
            String query = "INSERT INTO lotto_numbers_info (round, first, second, third, fourth, fifth, sixth) VALUES(?,?,?,?,?,?,?)";
            Connection connection = DBConnector.getConnection();
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, round);
            pstmt.setInt(2, lotto.getFirst());
            pstmt.setInt(3, lotto.getSecond());
            pstmt.setInt(4, lotto.getThird());
            pstmt.setInt(5, lotto.getFourth());
            pstmt.setInt(6, lotto.getFifth());
            pstmt.setInt(7, lotto.getSixth());
            pstmt.executeUpdate();
            DBConnector.closeConnection(connection);
        }
    }

    private int getLatestRound() throws SQLException {
        String query = "SELECT MAX(ROUND) as MAX_ROUND FROM lotto_numbers_info";
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

    public void fetchRequestLottos(int requestRound, List<String> lottos) throws SQLException {
        String query = "SELECT * FROM lotto_numbers_info WHERE round = ?";
        Connection connection = DBConnector.getConnection();
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setInt(1, requestRound);
        ResultSet resultSet = ps.executeQuery();
        while (resultSet.next()) {
            String result = "";
            result += resultSet.getInt("first");
            result += "-";
            result += resultSet.getInt("second");
            result += "-";
            result += resultSet.getInt("third");
            result += "-";
            result += resultSet.getInt("fourth");
            result += "-";
            result += resultSet.getInt("fifth");
            result += "-";
            result += resultSet.getInt("sixth");
            lottos.add(result);
        }
        DBConnector.closeConnection(connection);
    }
}
