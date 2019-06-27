package lotto.domain.dao;

import lotto.domain.lottomanager.WinningLotto;

public class WinningLottoDao extends LottoDaoUtils {
    private static final int ROUND_COLUMN = 1;
    private static final int BONUS_BALL = 8;
    private static final String INSERT_WINNING_LOTTO_QUERY = "INSERT INTO winningLotto(round, number_1, number_2, number_3, number_4" +
            ", number_5, number_6, bonusBall) Values(?, ?, ?, ?, ?, ?, ?, ?)";


    private static WinningLottoDao winningLottoDao = new WinningLottoDao();

    public static WinningLottoDao getInstance() {
        return winningLottoDao;
    }

    public void addWinningLotto(WinningLotto winningLotto) {
        DaoTemplate daoTemplate = (pstmt) -> {
            pstmt.setInt(ROUND_COLUMN, getRound());
            setLottoNumbers(pstmt, getLottoNumbers(winningLotto.getWinningLotto().getLottoTicket()));
            pstmt.setString(BONUS_BALL, winningLotto.getBonusBall().toString());
        };
        daoTemplate.insertTemplate(INSERT_WINNING_LOTTO_QUERY);
    }
}
