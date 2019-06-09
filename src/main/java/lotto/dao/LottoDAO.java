package lotto.dao;

import lotto.domain.Lotto;
import lotto.domain.Lottos;
import lotto.util.ConvertLottoNumber;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LottoDAO {
    public Connection getConnection() {
        Connection con = null;
        String server = "localhost:3307"; // MySQL 서버 주소
        String database = "lotto_game"; // MySQL DATABASE 이름
        String userName = "codemcd"; //  MySQL 서버 아이디
        String password = "0803"; // MySQL 서버 비밀번호

        // 드라이버 로딩
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println(" !! JDBC Driver load 오류: " + e.getMessage());
            e.printStackTrace();
        }

        // 드라이버 연결
        try {
            con = DriverManager.getConnection("jdbc:mysql://" + server + "/" + database +
                    "?serverTimezone=UTC&allowPublicKeyRetrieval=true&useSSL=false", userName, password);
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

    public void addLottos(String lottoId, Lottos lottos) throws SQLException {
        String query = "INSERT INTO lotto VALUES (?, ?)";
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        pstmt.setString(1, lottoId);
        StringBuilder stringLottos = new StringBuilder();
        List<String> strings = new ArrayList<>();
        for (Lotto lotto : lottos.getLottos()) {
            strings.add(lotto.toString());
        }
        stringLottos.append(String.join("\n", strings));
        pstmt.setString(2, stringLottos.toString());
        pstmt.executeUpdate();
    }

    public Lottos findByLottoId(String lottoId) throws SQLException {
        String query = "SELECT * FROM lotto WHERE lotto_id = ?";
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        pstmt.setString(1, lottoId);
        ResultSet rs = pstmt.executeQuery();

        if (!rs.next()) return null;

        List<Lotto> lottos = new ArrayList<>();
        for (String lotto : rs.getString("lottos").split("\n")) {
            lotto = lotto.substring(1, lotto.lastIndexOf("]"));
            lottos.add(new Lotto(ConvertLottoNumber.run(lotto)));
        }
        return new Lottos(lottos);
    }
}
