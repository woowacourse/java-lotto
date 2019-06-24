package lotto.domain.dao;

import lotto.DBUtils;
import lotto.domain.lottomanager.WinningLotto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class WinningLottoDao {
    private static final int ROUND = 1;
    private static final int BONUS_BALL = 8;

    private static WinningLottoDao winningLottoDao = new WinningLottoDao();

    public static WinningLottoDao getInstance() {
        return winningLottoDao;
    }

    public void addWinningLotto(WinningLotto winningLotto) throws SQLException {
        String insertWinningLottoQuery = "INSERT INTO winningLotto(round, number_1, number_2, number_3, number_4" +
                ", number_5, number_6, bonusBall) Values(?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = DBUtils.getConnection()) {
            int round = DBUtils.getRound(connection);
            PreparedStatement pstmt = connection.prepareStatement(insertWinningLottoQuery);

            pstmt.setInt(ROUND, round);
            DBUtils.setLottoNumbers(pstmt, DBUtils.getLottoNumbers(winningLotto.getWinningLotto().getLottoTicket()));
            pstmt.setString(BONUS_BALL, winningLotto.getBonusBall().toString());

            pstmt.executeUpdate();
        }
    }
}
