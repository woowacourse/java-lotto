package domain;

import domain.lottonumber.LottoNumber;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;

import static org.assertj.core.api.Assertions.assertThat;

class LottoFactoryTest {
    Set<LottoNumber> sixNumbers;

    @BeforeEach
    void setUp() {
        sixNumbers = new TreeSet<>(Arrays.asList(LottoNumber.valueOf(1),
                LottoNumber.valueOf(2), LottoNumber.valueOf(3),
                LottoNumber.valueOf(4), LottoNumber.valueOf(5),
                LottoNumber.valueOf(6)));
    }

    @Test
    void 입력받은_금액만큼_자동_로또를_제대로_발행해주는지_테스트() {
        PurchaseAmount purchaseAmount = PurchaseAmount.valueOf(5000);
        IssuedLottos issuedLottos = LottoFactory.autoIssueLottoWorthOf(purchaseAmount);
        Money purchasedAmount = issuedLottos.getPurchasedAmount();

        assertThat(purchasedAmount.getAmount()).isEqualTo(5000);
    }

    @Test
    void 당첨_로또를_제대로_발행해주는지_테스트() {
        LottoNumber bonusNumber = LottoNumber.valueOf(7);
        WinningLotto expectedWinningLotto = new WinningLotto(sixNumbers, bonusNumber);

        assertThat(LottoFactory.issueWinningLotto(Arrays.asList(1, 2, 3, 4, 5, 6), bonusNumber))
                .isEqualTo(expectedWinningLotto);
    }

    @Test
    void 수동으로_구매한_로또를_제대로_발행해주는지_테스트() {
        Lotto expectedLotto = new IssuedLotto(sixNumbers);

        assertThat(LottoFactory.manualIssueLottoBy(Arrays.asList(1, 2, 3, 4, 5, 6))).isEqualTo(expectedLotto);
    }
}