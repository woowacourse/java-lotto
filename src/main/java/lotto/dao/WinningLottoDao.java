package lotto.dao;


import lotto.config.DBUtils;
import lotto.domain.Lotto;
import lotto.domain.LottoNo;
import lotto.domain.WinningLotto;
import lotto.utils.ConverterToLottoNos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class WinningLottoDao {
    public int save(WinningLotto winningLotto, int round) {
        Connection conn = DBUtils.getConnection();
        PreparedStatement ps = null;
        int result = 0;

        try {
            String sql = "INSERT INTO winning_lotto (numbers, bonus_no, round) VALUES (?, ?, ?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1, winningLotto.getLottos());
            ps.setInt(2, winningLotto.getBonusNo());
            ps.setInt(3, round);
            result = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.close(conn, ps);
        }
        return result;
    }

    public WinningLotto findAllByRound(int round) {
        Connection conn = DBUtils.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            String sql = "SELECT * FROM winning_lotto WHERE round = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, round);
            rs = ps.executeQuery();

            if (rs.next()) {
                LottoNo bonusNo = LottoNo.from(rs.getInt("bonus_no"));
                Lotto lotto = Lotto.of(ConverterToLottoNos.convert(rs.getString("numbers")));
                return new WinningLotto(lotto, bonusNo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.close(conn, ps, rs);
        }
        return null;
    }
}
