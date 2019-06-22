package lotto.dao;

import lotto.domain.DBConnector;
import lotto.domain.creator.LottoCreator;
import lotto.domain.creator.ManualLottoCreator;
import lotto.domain.lotto.Lotto;
import lotto.domain.util.CustomStringUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LottoDAOImpl implements LottoDAO {
    private static final DBConnector CONNECTOR = DBConnector.getInstance();

    private static class LottoDAOImplHolder {
        private static final LottoDAO instance = new LottoDAOImpl();
    }

    public static LottoDAO getInstance() {
        return LottoDAOImplHolder.instance;
    }

    @Override
    public List<Lotto> findByRound(int round) {
        String query = "SELECT * FROM lotto WHERE round = ?";
        List<Lotto> lottos = new ArrayList<>();

        try (Connection con = CONNECTOR.getConnection();
             PreparedStatement pstmt = con.prepareStatement(query)) {

            pstmt.setInt(1, round);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                LottoCreator creator = new ManualLottoCreator(CustomStringUtils.parseInts(rs.getString("numbers")));
                Lotto lotto = creator.createLotto();

                lotto.setAuto(rs.getBoolean("is_auto"));
                lottos.add(lotto);
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lottos;
    }

    @Override
    public int addLotto(Lotto lotto, int round) {
        String query = "INSERT INTO lotto VALUES (?, ?, ?)";
        int result = 0;

        try (Connection con = CONNECTOR.getConnection();
             PreparedStatement pstmt = con.prepareStatement(query)) {

            pstmt.setInt(1, round);
            pstmt.setString(2, lotto.getNumbers().toString().replaceAll(("(\\[|])+"), ""));
            pstmt.setBoolean(3, lotto.isAuto());
            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public int deleteLotto(int round) {
        String query = "DELETE FROM lotto WHERE round=?";
        int result = 0;

        try (Connection con = CONNECTOR.getConnection();
             PreparedStatement pstmt = con.prepareStatement(query)) {

            pstmt.setInt(1, round);
            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
