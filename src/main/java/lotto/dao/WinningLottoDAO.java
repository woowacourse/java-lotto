package lotto.dao;

import lotto.dto.WinningLottoDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WinningLottoDAO {
    public Connection getConnection() {
        Connection con = null;
        String server = "localhost"; // MySQL 서버 주소
        String database = "lotto"; // MySQL DATABASE 이름
        String userName = "wooteco"; //  MySQL 서버 아이디
        String password = "1234"; // MySQL 서버 비밀번호

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

    // 드라이버 연결해제
    public void closeConnection(Connection con) {
        try {
            if (con != null)
                con.close();
        } catch (SQLException e) {
            System.err.println("con 오류:" + e.getMessage());
        }
    }

    public WinningLottoDTO findByWinningLottoId(String winningLottoId) throws SQLException {
        String query = "SELECT w.id, w.bonusBall, l.number " +
                        "FROM lotto.winninglotto as w " +
                        "JOIN (SELECT lotto.id as lotto_id, lotto.type, ln.number " +
                                "FROM lotto.lotto " +
                                "JOIN lotto.lottonumber as ln ON lotto.id = ln.lotto_id) as l " +
                        "ON l.lotto_id = w.lotto_id " +
                        "WHERE w.id = ?";
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        pstmt.setString(1, winningLottoId);
        ResultSet rs = pstmt.executeQuery();

        int id = 1, bonusBall = 1;
        List<Integer> numbers = new ArrayList<>();

        try {
            while (rs.next()) {
                id = rs.getInt("id");
                bonusBall = rs.getInt("bonusBall");
                numbers.add(rs.getInt("number"));
            }
        } catch (IndexOutOfBoundsException e) {
            return null;
        }

        return new WinningLottoDTO(
                id,
                numbers.get(0),numbers.get(1),numbers.get(2),numbers.get(3),numbers.get(4),numbers.get(5),
                bonusBall);
    }
}
