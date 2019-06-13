package lotto.domain.dao;

import lotto.domain.Rank;
import lotto.domain.dto.ResultDto;

import java.sql.*;
import java.util.Map;
import java.util.TreeMap;

public class ResultDao {
    public Connection getConnection() {
        Connection connection = null;
        String server = "seongmo.synology.me";
        String database = "techcourse_lotto";
        String userName = "techcourse";
        String password = "8IaSoMTzND7qeNuW";
        String portNumber = ":3307";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("!! JDBC Driver load 오류: " + e.getMessage());
            e.printStackTrace();
        }

        try {
            connection = DriverManager.getConnection("jdbc:mysql://" + server + portNumber + "/" +
                    database + "?useSSL=false&serverTimezone=UTC", userName, password);
            System.out.println("정상적으로 연결되었습니다~");
        } catch (SQLException e) {
            System.out.println("연결 오류: " + e.getMessage());
            e.printStackTrace();
        }

        return connection;
    }

    public void closeConnection(Connection connection) {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            System.err.println("con 오류: " + e.getMessage());
        }
    }

    public void addResult(int round, ResultDto resultDTO) throws SQLException {
        String query = "INSERT INTO lotto_result " +
                "(round, first, second, third, fourth, fifth, miss, yield, win_prize) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        int parameterIndex = 1;
        pstmt.setInt(parameterIndex++, round);
        for (Rank rank : Rank.values()) {
            pstmt.setInt(parameterIndex++, resultDTO.getWinnersCountByRank(rank));
        }
        pstmt.setDouble(parameterIndex++, resultDTO.getYield());
        pstmt.setDouble(parameterIndex, resultDTO.getWinPrize());
        pstmt.executeUpdate();
    }

    public Map<Rank, Integer> findWinnerCountByRound(int round) throws SQLException {
        String query = "SELECT FIRST,SECOND,THIRD,FOURTH,FIFTH,MISS FROM lotto_result WHERE round = ?";
        Map<Rank, Integer> winnerCount = new TreeMap<>();
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        pstmt.setInt(1, round);
        ResultSet rs = pstmt.executeQuery();

        if (!rs.next()) return null;

        for (Rank rank : Rank.values()) {
            winnerCount.put(rank,rs.getInt(rank.name()));
        }
        return winnerCount;
    }


    public double findYieldByRound(int round) throws SQLException {
        String query = "SELECT yield FROM lotto_result WHERE round = ?";
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        pstmt.setInt(1, round);
        ResultSet rs = pstmt.executeQuery();

        if (!rs.next()) return -1;

        return rs.getDouble("yield");
    }

    public long findWinPrizeByRound(int round) throws SQLException {
        String query = "SELECT win_prize FROM lotto_result WHERE round = ?";
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        pstmt.setInt(1, round);
        ResultSet rs = pstmt.executeQuery();

        if (!rs.next()) return -1;

        return rs.getLong("win_prize");
    }
}
