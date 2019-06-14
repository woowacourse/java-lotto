package lotto.model.dao;

import lotto.model.Lotto;
import lotto.model.Lottos;

import java.sql.*;
import java.util.List;

public class LottosDao {

    public Connection getConnection() {
        Connection connection = null;
        String server = "localhost";
        String database = "lotto_db";
        String userName = "kjmlotto";
        String password = "kjmlotto";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println(" !! JDBC Driver load 오류: " + e.getMessage());
            e.printStackTrace();
        }

        try {
            connection = DriverManager.getConnection("jdbc:mysql://" + server + "/" + database + "?serverTimezone=UTC&allowPublicKeyRetrieval=true&useSSL=false", userName, password);
            System.out.println("정상적으로 연결되었습니다");
        } catch (SQLException e) {
            System.err.println("연결 오류: " + e.getSQLState());
            e.printStackTrace();
        }
        return connection;
    }

    public void closeConnection(Connection connection) {
        try{
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            System.err.println("con 오류:" + e.getMessage());
        }
    }



    public void saveLottos(Lottos lottos) throws SQLException {
        int round = getLatestRound();
        for (Lotto lotto : lottos.getLottos()) {
            String query = "INSERT INTO lotto_numbers_info (round, first, second, third, fourth, fifth, sixth) VALUES(?,?,?,?,?,?,?)";
            PreparedStatement pstmt = getConnection().prepareStatement(query);
            pstmt.setInt(1, round);
            pstmt.setInt(2, lotto.getFirst());
            pstmt.setInt(3, lotto.getSecond());
            pstmt.setInt(4, lotto.getThird());
            pstmt.setInt(5, lotto.getFourth());
            pstmt.setInt(6, lotto.getFifth());
            pstmt.setInt(7, lotto.getSixth());
            pstmt.executeUpdate();
        }
    }

    private int getLatestRound() throws SQLException {
        String query = "SELECT MAX(ROUND) as MAX_ROUND FROM lotto_numbers_info";
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        ResultSet rs = pstmt.executeQuery();
        int round = 77;
        if (rs.next()) {
            round = rs.getInt("MAX_ROUND");
            System.out.println("제대로 작동");
        }
        System.out.println(round);
        return round + 1;
    }

    public void fetchRequestLottos(int requestRound, List<String> lottos) throws SQLException {
        String query = "SELECT * FROM lotto_numbers_info WHERE round = ?";
        PreparedStatement ps = getConnection().prepareStatement(query);
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
    }
}
