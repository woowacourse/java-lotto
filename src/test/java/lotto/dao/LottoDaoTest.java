package lotto.dao;

import lotto.domain.Lotto;
import lotto.domain.LottoNumberGenerator;
import lotto.domain.Number;
import lotto.domain.UserLotto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class LottoDaoTest {
    private List<Lotto> lotto;

    @BeforeEach
    void setUp() {
        List<Number> lottoNumbers = new ArrayList<>();

        for (int i = 1; i <= 6; i++) {
            lottoNumbers.add(Number.of(i));
        }

        lotto = new ArrayList<>();
        lotto.add(new Lotto(lottoNumbers));
        lotto.add(new Lotto(lottoNumbers));
    }

    @Test
    void addLotto() throws Exception {
        UserLotto userLotto = new UserLotto(lotto, 4, new LottoNumberGenerator());
        LottoDao.addLottoes(userLotto, 1);
    }

    @Test
    void maxRound() throws Exception {
        System.out.println(LottoDao.getMaxRound());
    }
}