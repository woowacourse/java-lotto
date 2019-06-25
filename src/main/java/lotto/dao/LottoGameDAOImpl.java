package lotto.dao;

import lotto.domain.DBConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LottoGameDAOImpl implements LottoGameDAO {
    private static final DBConnector CONNECTOR = DBConnector.getInstance();

    private static class LottoGameDAOImplHolder {
        private static final LottoGameDAO instance = new LottoGameDAOImpl();
    }

    public static LottoGameDAO getInstance() {
        return LottoGameDAOImplHolder.instance;
    }

    @Override
    public int getLastRound() {
        String query = "SELECT * from lotto_game ORDER BY round DESC limit 1";
        int round = 0;

        try (Connection con = CONNECTOR.getConnection();
             PreparedStatement pstmt = con.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {

            if (!rs.next()) return round;

            round = rs.getInt("round");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException("최근 회차를 가져오지 못했습니다.");
        }
        return round;
    }

    @Override
    public int addLottoGame(int round) {
        String query = "INSERT INTO lotto_game VALUES (?)";
        int result = 0;

        try (Connection con = CONNECTOR.getConnection();
             PreparedStatement pstmt = con.prepareStatement(query)) {

            pstmt.setInt(1, round);
            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException("로또 게임을 생성하지 못했습니다.");
        }
        return result;
    }

    @Override
    public int deleteLottoGame(int round) {
        String query = "DELETE FROM lotto_game WHERE round=?";
        int result = 0;

        try (Connection con = CONNECTOR.getConnection();
             PreparedStatement pstmt = con.prepareStatement(query)) {

            pstmt.setInt(1, round);
            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException("로또를 삭제하지 못했습니다.");
        }
        return result;
    }
}
