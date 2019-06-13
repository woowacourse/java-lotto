package lotto.dao;

import lotto.DataBase;
import lotto.domain.Lotto;
import lotto.domain.Number;
import lotto.domain.WinningLotto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

class WinningLottoDaoTest {
    private WinningLottoDao winningLottoDao;

    @BeforeEach
    void setUp() {
        winningLottoDao = new WinningLottoDao(new DataBase());
    }

    @Test
    void CRDWinningLotto() throws Exception{
        WinningLotto winningLotto = new WinningLotto(
                new Lotto(Arrays.asList(
                        Number.valueOf(6),
                        Number.valueOf(7),
                        Number.valueOf(8),
                        Number.valueOf(9),
                        Number.valueOf(10),
                        Number.valueOf(11)
                )), Number.valueOf(45)
        );

        assertThat(winningLottoDao.addWinningLotto(winningLotto, -1)).isEqualTo(1);
        assertThat(winningLottoDao.findByTimes(-1)).isEqualTo(winningLotto);
        assertThat(winningLottoDao.deleteWinningLotto(-1)).isEqualTo(1);
    }

    @Test
    void nextTimes() throws Exception {
        assertThat(winningLottoDao.newWinningLottoTimes()).isEqualTo(1);
    }
}