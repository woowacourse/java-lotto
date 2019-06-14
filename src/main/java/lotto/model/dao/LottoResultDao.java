package lotto.model.dao;

import com.mysql.cj.x.protobuf.MysqlxDatatypes;
import lotto.model.LottoResult;
import lotto.model.Money;

import java.sql.*;
import java.util.List;

public class LottoResultDao {
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

    public void saveResult(List<String> result, Money money, LottoResult lottoResult) throws SQLException {
        int round = getLatestRound();
        String query = "INSERT INTO lotto_result_info (round, result, profit_rate, prize_money) VALUES(?,?,?,?)";
        PreparedStatement ps = getConnection().prepareStatement(query);
        ps.setInt(1, round);
        ps.setString(2, String.join("\n", result));
        ps.setString(3, "" + lottoResult.calculateProfitRate());
        ps.setString(4, "" + lottoResult.calculateTotal());
        ps.executeUpdate();
    }

    private int getLatestRound() throws SQLException {
        String query = "SELECT MAX(ROUND) as MAX_ROUND FROM lotto_result_info";
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

    public void fetchRequestResult(int requestRound, List<String> results) throws SQLException {
        String query = "SELECT * FROM lotto_result_info WHERE round = ?";
        PreparedStatement ps = getConnection().prepareStatement(query);
        ps.setInt(1, requestRound);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            results.add(rs.getString("result"));
            results.add("당첨금액: \n");
            results.add(rs.getInt("prize_money") + "");
            results.add("수익률: \n");
            results.add(rs.getInt("profit_rate") + "%");
        }
    }
}

