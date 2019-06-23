package lotto.dao;

import lotto.domain.Lotto;
import lotto.domain.LottoNumber;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashSet;

import static org.assertj.core.api.Assertions.assertThat;

public class WinningLottoDAOTest {
    private static final Lotto  WINNING_LOTTO_TEST = new Lotto(new HashSet<>(
            Arrays.asList(
                    LottoNumber.get(1),
                    LottoNumber.get(2),
                    LottoNumber.get(3),
                    LottoNumber.get(4),
                    LottoNumber.get(5),
                    LottoNumber.get(6)
            )));
    private static final int BONUS_NUMBER = 10;

    private RoundDAO roundDAO;
    private LottoDAO lottoDAO;
    private WinningLottoDAO winningLottoDAO;

    @BeforeEach
    void setUp() throws SQLException {
        roundDAO = RoundDAO.getInstance();
        lottoDAO = LottoDAO.getInstance();
        winningLottoDAO = WinningLottoDAO.getInstance();
        roundDAO.addRound(RoundDAOTest.AMOUNT_TEST);
        lottoDAO.addLottos(Arrays.asList(LottoDAOTest.LOTTO_TEST));
    }

    @Test
    public void findWinningLottoByRound() throws SQLException {
        winningLottoDAO.addWinningLotto(WINNING_LOTTO_TEST, BONUS_NUMBER);
        Lotto winningLotto = winningLottoDAO.findWinningLottoByRound(roundDAO.findMaxRound());
        assertThat(winningLotto).isEqualTo(WINNING_LOTTO_TEST);
        removeWinningLotto();
    }

    @Test
    public void findBonusNumberByRound() throws SQLException {
        winningLottoDAO.addWinningLotto(WINNING_LOTTO_TEST, BONUS_NUMBER);
        int bonusNumber = winningLottoDAO.findBonusNumberByRound(roundDAO.findMaxRound());
        assertThat(bonusNumber).isEqualTo(BONUS_NUMBER);
        removeWinningLotto();
    }

    @Test
    public void addWinningLotto() throws SQLException {
        winningLottoDAO.addWinningLotto(WINNING_LOTTO_TEST, BONUS_NUMBER);
        removeWinningLotto();
    }

    @Test
    public void removeWinningLotto() throws SQLException {
        winningLottoDAO.removeWinningLotto(roundDAO.findMaxRound());
    }

    @AfterEach
    public void tearDown() throws SQLException {
        roundDAO.removeRound(roundDAO.findMaxRound());
        lottoDAO.removeLotto(roundDAO.findMaxRound());
        roundDAO = null;
        lottoDAO = null;
        winningLottoDAO = null;
    }
}
