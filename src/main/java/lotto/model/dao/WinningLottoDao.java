package lotto.model.dao;

import lotto.model.Lotto;
import lotto.model.LottoNumber;

import java.sql.*;
import java.util.List;

public class WinningLottoDao {
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

    public void saveWinningLotto(Lotto winningLottoTicket, LottoNumber bonusNumber) throws SQLException {
        int round = getLatestRound();
        String query = "INSERT INTO winning_lotto_info (round, first, second, third, fourth, fifth, sixth, bonus_number) VALUES(?,?,?,?,?,?,?,?)";
        PreparedStatement ps = getConnection().prepareStatement(query);
        ps.setInt(1, round);
        ps.setInt(2, winningLottoTicket.getFirst());
        ps.setInt(3, winningLottoTicket.getSecond());
        ps.setInt(4, winningLottoTicket.getThird());
        ps.setInt(5, winningLottoTicket.getFourth());
        ps.setInt(6, winningLottoTicket.getFifth());
        ps.setInt(7, winningLottoTicket.getSixth());
        ps.setInt(8, bonusNumber.getNumber());
        ps.executeUpdate();
    }

    public int getLatestRound() throws SQLException {
        String query = "SELECT MAX(ROUND) as MAX_ROUND FROM winning_lotto_info";
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

    public void fetchRequestWinningLotto(int requestRound, List<String> winningLotto) throws SQLException {
        String query = "SELECT * FROM winning_lotto_info WHERE round = ?";
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
            result += "-";
            result += resultSet.getInt("bonus_number");
            winningLotto.add(result);
        }
    }
}
