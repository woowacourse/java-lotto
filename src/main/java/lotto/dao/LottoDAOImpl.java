package lotto.dao;

import lotto.domain.DBConnector;
import lotto.domain.creator.LottoCreator;
import lotto.domain.creator.ManualLottoCreator;
import lotto.domain.lotto.Lotto;
import lotto.domain.util.CustomStringUtils;

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
    public List<Lotto> findByRound(int round) throws SQLException {
        String query = "SELECT * FROM lotto WHERE round = ?";
        PreparedStatement pstmt = CONNECTOR.getConnection().prepareStatement(query);
        pstmt.setInt(1, round);
        ResultSet rs = pstmt.executeQuery();

        List<Lotto> lottos = new ArrayList<>();
        while (rs.next()) {
            LottoCreator creator = new ManualLottoCreator(CustomStringUtils.parseInts(rs.getString("numbers")));
            Lotto lotto = creator.createLotto();
            lotto.setIsAuto(rs.getBoolean("is_auto"));
            lottos.add(lotto);
        }
        return lottos;
    }

    @Override
    public int addLotto(Lotto lotto, int round) throws SQLException {
        String query = "INSERT INTO lotto VALUES (?, ?, ?)";
        PreparedStatement pstmt = CONNECTOR.getConnection().prepareStatement(query);
        pstmt.setInt(1, round);
        pstmt.setString(2, lotto.getNumbers().toString().replaceAll(("(\\[|])+"), ""));
        pstmt.setBoolean(3, lotto.getIsAuto());
        return pstmt.executeUpdate();
    }

    @Override
    public int deleteLotto(int round) throws SQLException {
        String query = "DELETE FROM lotto WHERE round=?";
        PreparedStatement pstmt = CONNECTOR.getConnection().prepareStatement(query);
        pstmt.setInt(1, round);
        return pstmt.executeUpdate();
    }
}
