package lotto.dao;

import lotto.domain.*;
import lotto.domain.Number;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class LottoDaoTest {
    private List<Lotto> lotto;
    private List<Number> lottoNumbers;

    @BeforeEach
    void setUp() {
        lottoNumbers = new ArrayList<>();

        for (int i = 1; i <= 6; i++) {
            lottoNumbers.add(Number.of(i));
        }

        lotto = new ArrayList<>();
        lotto.add(new Lotto(lottoNumbers));
        lotto.add(new Lotto(lottoNumbers));
    }

    @Test
    void addLotto() {
        UserLotto userLotto = new UserLotto(lotto, 4, new LottoNumberGenerator());
        LottoDao.addLotto(userLotto, 1);
    }

    @Test
    void addWinningLotto() {
        Lotto lotto = new Lotto(lottoNumbers);
        WinningLotto winningLotto = new WinningLotto(lotto, Number.of(9));
        LottoDao.addWinningLotto(winningLotto, 9);
    }

    @Test
    void addResult() {
        List<String> results = new ArrayList<>();
        results.add("FIRST");
        results.add("SECOND");
        LottoDao.addResult(results, 7);
    }

    @Test
    void addResultInfo() {
        LottoDao.addResultInfo(4, 2.0, "300000");
    }

    @Test
    void maxRound() {
        System.out.println(LottoDao.offerMaxRound());
    }
}