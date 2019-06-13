package lotto.domain.dao;

import lotto.domain.Lotto;
import lotto.domain.LottoNumber;
import lotto.domain.Lottos;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LottoDao extends Connector {
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