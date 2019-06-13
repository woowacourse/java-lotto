package lotto.domain.dao;

import lotto.domain.WinningLotto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WinningLottoDao {
    public Connection getConnection() {
        Connection connection = null;
        String server = "seongmo.synology.me"; // MySQL 서버 주소
        String database = "techcourse_lotto"; // MySQL DATABASE 이름
        String userName = "techcourse"; //  MySQL 서버 아이디
        String password = "8IaSoMTzND7qeNuW"; // MySQL 서버 비밀번호
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

    public void addWinningLotto(int round, WinningLotto winningLotto) throws SQLException {
        String query = "INSERT INTO winning_lotto (round, lotto_num1, lotto_num2, lotto_num3, lotto_num4, lotto_num5, lotto_num6, bonus_num) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        pstmt.setInt(1, round);
        for (int i = 0; i < 6; i++) {
            pstmt.setInt(i + 2, winningLotto.getWinningLottoValueByIndex(i));
        }
        pstmt.setInt(8, winningLotto.getBonusBallValue());
        pstmt.executeUpdate();
    }

    public List<Integer> findWinningLottoByRound(int round) throws SQLException {
        String query = "SELECT lotto_num1, lotto_num2, lotto_num3, lotto_num4, lotto_num5, lotto_num6 FROM winning_lotto WHERE round = ?";
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        pstmt.setInt(1, round);
        ResultSet rs = pstmt.executeQuery();

        if (!rs.next()) return null;

        List<Integer> winningLotto = new ArrayList<>();
        for (int i = 1; i <= 6; i++) {
            winningLotto.add(rs.getInt("lotto_num" + i));
        }
        return winningLotto;
    }

    public int findBonusNumByRound(int round) throws SQLException {
        String query = "SELECT bonus_num FROM winning_lotto WHERE round = ?";
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        pstmt.setInt(1, round);
        ResultSet rs = pstmt.executeQuery();

        if (!rs.next()) return -1;

        return rs.getInt("bonus_num");
    }
}
