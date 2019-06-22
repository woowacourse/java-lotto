package lotto.dao;

import lotto.db.SettingLotto;
import lotto.domain.Lotto;
import lotto.domain.LottoNumber;
import lotto.domain.WinningLotto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class WinningLottoDao {
    private static final String INSERT_WINNING_LOTTO_SQL = "INSERT INTO winninglotto(win_1,win_2,win_3,win_4,win_5,win_6,bonus,round_id) values(?,?,?,?,?,?,?,?)";
    private static final String SELECT_WINNING_LOTTO_SQL = "SELECT win_1,win_2,win_3,win_4,win_5,win_6,bonus FROM winninglotto WHERE round_id=?";
    private static final String BONUS = "bonus";
    private static final String COL_WIN_NAME = "win";


    private final Connection conn;

    public WinningLottoDao(final Connection conn) {
        this.conn = conn;
    }

    public void addWinningLotto(int round, WinningLotto winningLotto) throws SQLException {
        PreparedStatement pstmt = conn.prepareStatement(INSERT_WINNING_LOTTO_SQL);
        Lotto lotto = winningLotto.getWinningNumbers();
        LottoNumber bonus = winningLotto.getBonus();
        SettingLotto.setLottoInDB(pstmt, lotto);
        pstmt.setInt(7, bonus.getNumber());
        pstmt.setInt(8, round);
        pstmt.executeUpdate();
    }

    public WinningLotto findWinningLottoByRound(int round) throws SQLException {
        PreparedStatement pstmt = conn.prepareStatement(SELECT_WINNING_LOTTO_SQL);
        pstmt.setInt(1, round);
        ResultSet rs = pstmt.executeQuery();

        if (rs.next()) {
            LottoNumber bonus = LottoNumber.valueOf(rs.getInt(BONUS));
            return new WinningLotto(SettingLotto.getLottoInDB(rs, COL_WIN_NAME), bonus);
        }
        throw new SQLException("요청하신 round에 해당하는 winningLotto가 없습니다!");
    }

}
