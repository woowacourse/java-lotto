package lotto.dao;

import lotto.dao.sqls.WinningLottoSQLs;
import lotto.domain.lotto.Lotto;
import lotto.domain.lotto.WinningLotto;
import lotto.utils.DBUtils;

import java.sql.*;

public class WinningLottoDAO {
    private static WinningLottoDAO winningLottoDAO = new WinningLottoDAO();
    private static final int START_COLUMN_NUMBER_OF_WINNING_LOTTO_TABLE = 1;

    private WinningLottoDAO() {
    }

    public static WinningLottoDAO getInstance() {
        return winningLottoDAO;
    }

    public int addWinningLotto(WinningLotto winningLotto) throws SQLException {
        String query = WinningLottoSQLs.INSERT_WINNING_LOTTO;

        try (Connection connection = DBUtils.getConnection()) {
            PreparedStatement pstmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            Lotto lotto = winningLotto.getWinningLotto();
            DBUtils.setLottoNumber(pstmt, lotto, START_COLUMN_NUMBER_OF_WINNING_LOTTO_TABLE);
            pstmt.setInt(START_COLUMN_NUMBER_OF_WINNING_LOTTO_TABLE + Lotto.LOTTO_NUMBER_SIZE,
                    winningLotto.getBonusNumber().getNumber());
            pstmt.executeUpdate();

            ResultSet resultSet = pstmt.getGeneratedKeys();

            if (!resultSet.next()) {
                return -1;
            }

            return resultSet.getInt(1);
        }
    }
}
