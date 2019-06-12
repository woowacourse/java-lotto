package lotto.domain.DAO;

import lotto.domain.User;

import java.sql.*;

public class WinningLottoDAO {
    public Connection getConnection() {
        Connection con = null;
        String server = "localhost"; // MySQL 서버 주소
        String database = "lottoDB"; // MySQL DATABASE 이름
        String userName = "kangmin46"; //  MySQL 서버 아이디
        String password = "rkdals46"; // MySQL 서버 비밀번호

        // 드라이버 로딩
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println(" !! JDBC Driver load 오류: " + e.getMessage());
            e.printStackTrace();
        }

        // 드라이버 연결
        try {
            con = DriverManager.getConnection("jdbc:mysql://" + server + "/" + database + "?serverTimezone=UTC" +
                    "&allowPublicKeyRetrieval=true&useSSL=false", userName, password);
            System.out.println("정상적으로 연결되었습니다.");
        } catch (SQLException e) {
            System.err.println("연결 오류:" + e.getMessage());
            e.printStackTrace();
        }

        return con;
    }


    public void closeConnection(Connection con) {
        try {
            if (con != null)
                con.close();
        } catch (SQLException e) {
            System.err.println("con 오류:" + e.getMessage());
        }
    }

    public void addWinningLottoInfo(int lottoRound, String winningNumber, int bonusBall) throws SQLException {
        String query = "INSERT INTO winning_lotto_info VALUES (?, ?, ?)";
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        pstmt.setInt(1,lottoRound);
        pstmt.setString(2, winningNumber);
        pstmt.setInt(3,bonusBall);
        pstmt.executeUpdate();
    }

    public void deleteWinningLottoInfoByLottoRound(int lottoRound) throws SQLException{
        String query =  "DELETE FROM winning_lotto_info where lottoRound=?";
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        pstmt.setInt(1,lottoRound);
        pstmt.executeUpdate();
    }

}
