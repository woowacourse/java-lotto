package domain;

import domain.lottonumber.LottoNumber;
import domain.money.Money;
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
        lottoNumbersOfOneToSix = new TreeSet<>(Arrays.asList(LottoNumber.valueOf(1),
                LottoNumber.valueOf(2), LottoNumber.valueOf(3),
                LottoNumber.valueOf(4), LottoNumber.valueOf(5),
                LottoNumber.valueOf(6)));
        lottoNumbersOfTwoToSeven = new TreeSet<>(Arrays.asList(LottoNumber.valueOf(2),
                LottoNumber.valueOf(3), LottoNumber.valueOf(4),
                LottoNumber.valueOf(5), LottoNumber.valueOf(6),
                LottoNumber.valueOf(7)));
    }

    @Test
    void 발급된_로또들의_총_구매_가격을_되돌려주는지_테스트() {
        IssuedLottos issuedLottos = IssuedLottos.of(Arrays.asList(new Lotto(lottoNumbersOfOneToSix)));
        Money purchaseAmount = issuedLottos.getPurchasedAmount();

        assertThat(purchaseAmount.getAmount()).isEqualTo(1000);
    }

    @Test
    void 발급된_로또들을_하나의_콜렉션으로_합치는지_테스트() {
        IssuedLottos lottoGroupA = IssuedLottos.of(Arrays.asList(new Lotto(lottoNumbersOfOneToSix)));
        IssuedLottos lottoGroupB = IssuedLottos.of(Arrays.asList(new Lotto(lottoNumbersOfTwoToSeven)));

        assertThat(IssuedLottos.join(lottoGroupA, lottoGroupB).getLottos())
                .isEqualTo(Arrays.asList(new Lotto(lottoNumbersOfOneToSix), new Lotto(lottoNumbersOfTwoToSeven)));
    }
}

