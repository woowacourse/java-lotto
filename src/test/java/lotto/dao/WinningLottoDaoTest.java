package lotto.dao;

import lotto.domain.Lotto;
import lotto.domain.Number;
import lotto.domain.WinningLotto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class WinningLottoDaoTest {
    private List<Number> lottoNumbers = new ArrayList<>();
    private WinningLottoDao winningLottoDao = WinningLottoDao.getInstance();

    @BeforeEach
    void setUp() {
        for (int i = 1; i <= 6; i++) {
            lottoNumbers.add(Number.of(i));
        }
    }

    @Test
    void addWinningLotto() {
        Lotto lotto = new Lotto(lottoNumbers);
        WinningLotto winningLotto = new WinningLotto(lotto, Number.of(9));
        winningLottoDao.addWinningLotto(winningLotto, 9);
    }

    @Test
    void offerWinningNumber() {
        System.out.println(winningLottoDao.offerWinningNumber(2));
    }

    @Test
    void offerBonusNumber() {
        System.out.println(winningLottoDao.offerBonusNumber(2));
    }
}