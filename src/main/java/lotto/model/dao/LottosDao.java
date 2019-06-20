package lotto.model.dao;

import lotto.model.Lotto;
import lotto.model.Lottos;
import lotto.model.config.DBConnector;

import java.sql.*;
import java.util.ArrayList;
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

    public void deleteLottos(int targetRound) throws SQLException {
        String query = "DELETE FROM lotto_numbers_info WHERE round = ?";
        PreparedStatement ps = DBConnector.getConnection().prepareStatement(query);
        ps.setInt(1, targetRound - 1);
        ps.executeUpdate();
    }

    public int getLatestRound() throws SQLException {
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

    public List<String> fetchRequestLottos(int requestRound) throws SQLException {
        List<String> lottos = new ArrayList<>();
        String query = "SELECT * FROM lotto_numbers_info WHERE round = ?";
        Connection connection = DBConnector.getConnection();
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setInt(1, requestRound);
        ResultSet resultSet = ps.executeQuery();

        retrieveDataFromDB(resultSet, lottos);

        DBConnector.closeConnection(connection);
        return lottos;
    }

    private void retrieveDataFromDB(ResultSet resultSet, List<String> lottos) throws SQLException {
        while (resultSet.next()) {
            List<String> numbers = new ArrayList<>();
            numbers.add(Integer.toString(resultSet.getInt("first")));
            numbers.add(Integer.toString(resultSet.getInt("second")));
            numbers.add(Integer.toString(resultSet.getInt("third")));
            numbers.add(Integer.toString(resultSet.getInt("fourth")));
            numbers.add(Integer.toString(resultSet.getInt("fifth")));
            numbers.add(Integer.toString(resultSet.getInt("sixth")));
            lottos.add(String.join(",", numbers));
        }
    }
}
