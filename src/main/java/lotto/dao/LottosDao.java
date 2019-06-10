package lotto.dao;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lotto.domain.Lotto;
import lotto.domain.Lottos;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LottosDao {
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

    // 드라이버 연결해제
    public void closeConnection(Connection con) {
        try {
            if (con != null)
                con.close();
        } catch (SQLException e) {
            System.err.println("con 오류:" + e.getMessage());
        }
    }

    public int[] addLottos(Lottos lottos, int times) throws SQLException {
        String query = "INSERT INTO lotto (numbers, times) VALUES (?, ?)";
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        Gson gson = new GsonBuilder().create();

        for (Lotto lotto : lottos.getLottos()) {
            pstmt.setString(1, gson.toJson(lotto));
            pstmt.setInt(2, times + 1);
            pstmt.addBatch();
        }

        return pstmt.executeBatch();
    }

    public Lottos findByTimes(int times) throws SQLException {
        String query = "SELECT * FROM lotto WHERE times = ?";
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        pstmt.setInt(1, times);
        ResultSet rs = pstmt.executeQuery();

        List<Lotto> lottos = new ArrayList<>();

        Gson gson = new Gson();

        while (rs.next()) {
            Lotto lotto = gson.fromJson(rs.getString("numbers"), Lotto.class);
            lottos.add(lotto);
        }
        return new Lottos(lottos);
    }

}
