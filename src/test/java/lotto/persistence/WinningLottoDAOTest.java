package lotto.persistence;

import lotto.domain.WinningLotto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class WinningLottoDAOTest {
    private WinningLottoDAO winningLottoDao;
    private RoundDAO roundDao;
    private WinningLotto winningLotto;

    @BeforeEach
    void setUp() {
        this.winningLottoDao = WinningLottoDAO.getInstance();
        this.roundDao = RoundDAO.getInstance();
        this.winningLotto = new WinningLotto(Arrays.asList(1, 2, 3, 4, 5, 6), 7);
    }

    @Test
    void addWinningLottoTest() {
        roundDao.addRound(100, 0.1);
        int roundId = roundDao.getLatestRoundId();
        winningLottoDao.addWinningLotto(roundId, winningLotto);
        assertThat(winningLottoDao.findWinningLottoByRoundId(roundId)).isEqualTo(winningLotto);
        roundDao.removeRoundById(roundId);
    }
}
