package lotto.domain.dao;

import lotto.domain.Lotto;
import lotto.domain.LottoNumber;
import lotto.domain.Lottos;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LottoDao {
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

    public void addTotalLottos(int round, Lottos lottos) throws SQLException {
        for (int i = 0; i < lottos.getLottoCount(); i++) {
            addLotto(round, lottos.getLottoByIndex(i));
        }
    }

    private void addLotto(int round, Lotto lotto) throws SQLException {
        String query = "INSERT INTO lotto (round, lotto_num1, lotto_num2, lotto_num3, lotto_num4, lotto_num5, lotto_num6) VALUES (?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        pstmt.setInt(1, round);
        for (int i = 0; i < 6; i++) {
            pstmt.setInt(i + 2, lotto.getLottoNumberByIndex(i));
        }
        pstmt.executeUpdate();
    }

    public List<Lotto> findLottoByRound(int round) throws SQLException {
        String query = "SELECT * FROM lotto WHERE round = ?";
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        pstmt.setInt(1, round);
        ResultSet rs = pstmt.executeQuery();

        if (!rs.next()) return null;

        List<Lotto> lottos = new ArrayList<>();
        rs.last();
        int rowCount = rs.getRow();
        rs.first();
        for (int i = 0; i < rowCount; i++) {
            lottos.add(new Lotto(getLotto(rs)));
            rs.next();
        }

        return lottos;
    }

    private List<LottoNumber> getLotto(ResultSet rs) throws SQLException {
        List<LottoNumber> lotto = new ArrayList<>();
        for (int i = 1; i <= 6; i++) {
            lotto.add(LottoNumber.from(rs.getInt("lotto_num" + i)));
        }
        return lotto;
    }

    public void deleteLottos(int round) throws SQLException {
        String query = "DELETE FROM lotto WHERE round = ?";
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        pstmt.setInt(1, round);
        pstmt.executeUpdate();
    }
}