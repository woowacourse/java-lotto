package domain;

import domain.lottonumber.LottoNumber;
import domain.lottonumber.LottoNumberPool;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;

import static org.assertj.core.api.Assertions.assertThat;

class IssuedLottosTest {
    private Set<LottoNumber> lottoNumbersOfOneToSix;
    private Set<LottoNumber> lottoNumbersOfTwoToSeven;

    @BeforeEach
    void setUp() {
        lottoNumbersOfOneToSix = new TreeSet<>(Arrays.asList(LottoNumberPool.pickLottoNumber(1),
                LottoNumberPool.pickLottoNumber(2), LottoNumberPool.pickLottoNumber(3),
                LottoNumberPool.pickLottoNumber(4), LottoNumberPool.pickLottoNumber(5),
                LottoNumberPool.pickLottoNumber(6)));
        lottoNumbersOfTwoToSeven = new TreeSet<>(Arrays.asList(LottoNumberPool.pickLottoNumber(2),
                LottoNumberPool.pickLottoNumber(3), LottoNumberPool.pickLottoNumber(4),
                LottoNumberPool.pickLottoNumber(5), LottoNumberPool.pickLottoNumber(6),
                LottoNumberPool.pickLottoNumber(7)));
    }

    @Test
    void 발급된_로또들의_총_구매_가격을_되돌려주는지_테스트() {
        IssuedLottos issuedLottos = IssuedLottos.of(Arrays.asList(new IssuedLotto(lottoNumbersOfOneToSix)));
        Money purchaseAmount = issuedLottos.getPurchasedAmount();

        assertThat(purchaseAmount.getAmount()).isEqualTo(1000);
    }

    @Test
    void 발급된_로또들을_하나의_콜렉션으로_합치는지_테스트() {
        IssuedLottos lottoGroupA = IssuedLottos.of(Arrays.asList(new IssuedLotto(lottoNumbersOfOneToSix)));
        IssuedLottos lottoGroupB = IssuedLottos.of(Arrays.asList(new IssuedLotto(lottoNumbersOfTwoToSeven)));

        assertThat(IssuedLottos.getTotalLottosOf(lottoGroupA, lottoGroupB).getLottos())
                .isEqualTo(Arrays.asList(new IssuedLotto(lottoNumbersOfOneToSix), new IssuedLotto(lottoNumbersOfTwoToSeven)));
    }
}

