package lotto.dao;

import lotto.domain.DBConnector;
import lotto.domain.lotto.WinningLotto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class WinningLottoDAOImpl implements WinningLottoDAO {
    private static final String regexForDelteBracket = "(\\[|])+";
    private static final DBConnector CONNECTOR = DBConnector.getInstance();

    private static class WinningLottoDAOImplHolder {
        private static final WinningLottoDAO instance = new WinningLottoDAOImpl();
    }

    public static WinningLottoDAO getInstance() {
        return WinningLottoDAOImplHolder.instance;
    }

    @Override
    public WinningLotto findByRound(int round) {
        String query = "SELECT * FROM winning_lotto WHERE round = ?";
        WinningLotto winningLotto = null;

        try (Connection con = CONNECTOR.getConnection();
             PreparedStatement pstmt = con.prepareStatement(query)) {

            pstmt.setInt(1, round);
            ResultSet rs = pstmt.executeQuery();

            if (!rs.next()) return winningLotto;

            winningLotto = new WinningLotto(rs.getString("numbers"),
                                            rs.getString("bonus_number"));
            rs.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException("당첨 로또를 가져오지 못했습니다.");
        }
        return winningLotto;
    }

    @Override
    public int addWinningLotto(WinningLotto winningLotto, int round) {
        String query = "INSERT INTO winning_lotto VALUES (?, ?, ?)";
        int result = 0;

        try (Connection con = CONNECTOR.getConnection();
             PreparedStatement pstmt = con.prepareStatement(query)) {

            pstmt.setInt(1, round);
            pstmt.setString(2,
                    winningLotto.getLotto().getNumbers().toString().replaceAll((regexForDelteBracket), ""));
            pstmt.setString(3, winningLotto.getBonusNumber().toString());
            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException("당첨 로또를 생성하지 못했습니다.");
        }
        return result;
    }

    @Override
    public int deleteWinningLotto(int round) {
        String query = "DELETE FROM winning_lotto WHERE round=?";
        int result = 0;

        try (Connection con = CONNECTOR.getConnection();
             PreparedStatement pstmt = con.prepareStatement(query)) {

            pstmt.setInt(1, round);
            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException("당첨 로또를 삭제하지 못했습니다.");
        }
        return result;
    }
}
