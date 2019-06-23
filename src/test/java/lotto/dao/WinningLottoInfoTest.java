package lotto.dao;

import lotto.domain.Lotto;
import lotto.domain.Number;
import lotto.domain.WinningLotto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class WinningLottoInfoTest {
    private List<Number> lottoNumbers = new ArrayList<>();
    private WinningLottoInfo winningLottoInfo = WinningLottoInfo.getInstance();

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
        winningLottoInfo.addWinningLotto(winningLotto, 9);
    }

    @Test
    void offerWinningNumber() {
        System.out.println(winningLottoInfo.offerWinningNumber(2));
    }

    @Test
    void offerBonusNumber() {
        System.out.println(winningLottoInfo.offerBonusNumber(2));
    }
}