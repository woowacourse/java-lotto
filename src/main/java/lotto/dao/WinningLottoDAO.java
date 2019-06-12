package lotto.dao;

import lotto.domain.Lotto;
import lotto.domain.LottoNumber;
import lotto.domain.WinningLotto;
import lotto.utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class WinningLottoDAO {
    private static final String INSERT_WINNING_LOTTO_SQL = "INSERT INTO winninglotto(win_1,win_2,win_3,win_4,win_5,win_6,bonus,round_id) values(?,?,?,?,?,?,?,?)";
    private static final String SELECT_WINNING_LOTTO_SQL = "SELECT * FROM winninglotto WHERE round_id=?";

    private final Connection conn;

    public WinningLottoDAO(final Connection conn) {
        this.conn = conn;
    }

    public void addWinningLotto(int round, WinningLotto winningLotto) throws SQLException {
        PreparedStatement pstmt = conn.prepareStatement(INSERT_WINNING_LOTTO_SQL);
        Lotto lotto = winningLotto.getWinningNumbers();
        LottoNumber bonus = winningLotto.getBonus();
        DBUtils.setLottoInDB(pstmt, lotto);
        pstmt.setInt(7, bonus.getNumber());
        pstmt.setInt(8, round);
        pstmt.executeUpdate();
    }

    public WinningLotto findWinningLottoByRound(int round) throws SQLException {
        PreparedStatement pstmt = conn.prepareStatement(SELECT_WINNING_LOTTO_SQL);
        pstmt.setInt(1, round);
        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
            LottoNumber bonus = LottoNumber.valueOf(rs.getInt("bonus"));
            return new WinningLotto(DBUtils.getLottoInDB(rs, "win"), bonus);
        }
        return null;

    }

}
