package lotto.dao;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lotto.domain.WinningLotto;
import lotto.dto.WinningLottoDTO;

import java.sql.*;

public class WinningLottoDao {
    public Connection getConnection() {
        Connection con = null;
        String server = "localhost"; // MySQL 서버 주소
        String database = "lotto_db"; // MySQL DATABASE 이름
        String userName = "user"; //  MySQL 서버 아이디
        String password = "gmlgus12"; // MySQL 서버 비밀번호

        // 드라이버 로딩
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println(" !! JDBC Driver load 오류: " + e.getMessage());
            e.printStackTrace();
        }

        // 드라이버 연결
        try {
            con = DriverManager.getConnection("jdbc:mysql://" + server + "/" + database + "?serverTimezone=UTC&useSSL=false", userName, password);
            System.out.println("정상적으로 연결되었습니다.");
        } catch (SQLException e) {
            System.err.println("연결 오류:" + e.getMessage());
            e.printStackTrace();
        }

        return con;
    }

    public int addWinningLotto(WinningLotto winningLotto) throws SQLException {
        String query = "INSERT INTO winning_lotto VALUES (?, ?, ?)";
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        int times = countWinningLottoTimes();
        Gson gson = new GsonBuilder().create();

        pstmt.setInt(1, times + 1);
        pstmt.setString(2, gson.toJson(winningLotto.getWinningLotto()));
        pstmt.setString(3, gson.toJson(winningLotto.getBonusNum()));
        return pstmt.executeUpdate();
    }

    public int countWinningLottoTimes() throws SQLException {
        String query = "SELECT COUNT(*) FROM winning_lotto";
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        ResultSet rs = pstmt.executeQuery();

        if (!rs.next()) return 0;

        return Integer.parseInt(rs.getString(1));
    }
}
