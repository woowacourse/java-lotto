package lotto.dao;

import lotto.domain.*;
import lotto.domain.Number;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class LottoInfoTest {
    private List<Lotto> lotto;
    private LottoInfo lottoInfo = LottoInfo.getInstance();

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
    void addLotto() {
        UserLotto userLotto = new UserLotto(lotto, 4, new LottoNumberGenerator());
        lottoInfo.addLotto(userLotto, 1);
    }

    @Test
    void offerMaxRound() {
        System.out.println(lottoInfo.offerMaxRound());
    }

    @Test
    void offerUserLottoNumber() {
        System.out.println(lottoInfo.offerUserLottoNumber(1));
    }
}