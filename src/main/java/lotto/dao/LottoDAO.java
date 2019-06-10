package lotto.dao;

import lotto.domain.Lotto;
import lotto.domain.LottoFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// TODO try-resource 적용해보기
public class LottoDAO {
    public Connection getConnection() {
        Connection con = null;
        String server = "localhost";
        String database = "wooteco";
        String userName = "user";
        String password = "1234";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println(" !! JDBC Driver load 오류: " + e.getMessage());
            e.printStackTrace();
        }

        try {
            con = DriverManager.getConnection("jdbc:mysql://" + server + "/" + database + "?serverTimezone=UTC", userName, password);
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
                System.out.println("정상적으로 해제되었습니다.");
                con.close();
        } catch (SQLException e) {
            System.err.println("con 오류:" + e.getMessage());
        }
    }


    public int findMaxRound() throws SQLException {
        Connection connection = getConnection();
        String sql = "SELECT MAX(round) AS max FROM lotto";
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();

        if (!resultSet.next()) {
            return 1;
        }
        int result = resultSet.getInt("max");
        closeConnection(connection);
        return result;
    }

    public List<Lotto> findLottosByRound(int round) throws SQLException {
        Connection connection = getConnection();
        String sql = "SELECT lotto FROM lotto WHERE round = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, round);
        ResultSet resultSet = statement.executeQuery();
        List<Lotto> lottos = new ArrayList<>();
        while (resultSet.next()) {
            Lotto lotto = LottoFactory.createLottoManually(
                    Arrays.asList(resultSet.getString("lotto")
                            .replaceAll(" ", "")
                            .split(",")));
            lottos.add(lotto);
        }
        closeConnection(connection);
        return lottos;
    }

    public void addLottos(List<Lotto> lottos) throws SQLException {
        Connection connection = getConnection();
        String sql = "INSERT INTO lotto(lotto, round) VALUES (?, ?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        int nextRound = findMaxRound() + 1;
        for(Lotto lotto : lottos) {
            statement.setString(1, lotto.toString().substring(1, lotto.toString().length() - 1));
            statement.setInt(2, nextRound);
            statement.executeUpdate();
        }

        closeConnection(connection);
    }

    public void removeLotto(int lottoId) throws SQLException {
        Connection connection = getConnection();
        String sql = "DELETE FROM lotto WHERE round = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, lottoId);
        statement.executeUpdate();

        closeConnection(connection);
    }
}
