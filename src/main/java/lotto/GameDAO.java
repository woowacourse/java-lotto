package lotto;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import lotto.domain.game.LottoResult;
import lotto.view.OutputView;

public class GameDAO {
    public Connection getConnection() {
        Connection con = null;
        String server = "localhost";
        String database = "lotto";
        String userName = "luffy";
        String password = "159456";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println(" !! JDBC Dirver load 오류 : " + e.getMessage());
            e.printStackTrace();
        }

        try {
            con = DriverManager.getConnection("jdbc:mysql://" + server + "/" + database + "?useSSL=false&serverTimezone=UTC", userName, password);
            System.out.println("정상적으로 연결되었습니다.");
        } catch (SQLException e) {
            System.err.println("연결 오류 : " + e.getMessage());
            e.printStackTrace();
        }

        return con;
    }

    public void closeConnection(Connection con) {
        try {
            if (con != null) {
                con.close();
            }
        } catch (SQLException e) {
            System.err.println("con 오류:" + e.getMessage());
        }
    }

    public void add(GameDTO gameDTO) throws SQLException {
        String query = "INSERT INTO games(winning_numbers, bonus, result, return_amount, return_rate) VALUES(?, ?, ?, ?, ?)";
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        pstmt.setString(1, gameDTO.getWinningNumbers().toString());
        pstmt.setString(2, gameDTO.getBonusNumber().toString());
        pstmt.setString(3, gameDTO.getResult());
        pstmt.setString(4, gameDTO.getReturnAmount());
        pstmt.setString(5, gameDTO.getReturnRate());
        pstmt.executeUpdate();
    }
}
