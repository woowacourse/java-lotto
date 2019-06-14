package lotto.domain.dao;

import lotto.domain.Lotto;
import lotto.domain.LottoNumber;
import lotto.domain.Lottos;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LottoDao extends Connector {
    private static final int LOTTO_COUNT = 6;

    public void addTotalLottos(int round, Lottos lottos) throws SQLException {
        for (int i = 0; i < lottos.getLottoCount(); i++) {
            addLotto(round, lottos.getLottoByIndex(i));
        }
    }

    private void addLotto(int round, Lotto lotto) throws SQLException {
        String query = "INSERT INTO lotto (round, num1, num2, num3, num4, num5, num6) VALUES (?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        pstmt.setInt(1, round);
        for (int i = 0; i < LOTTO_COUNT; i++) {
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
        for (int i = 1; i <= LOTTO_COUNT; i++) {
            lotto.add(LottoNumber.from(rs.getInt("num" + i)));
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