package domain;

import domain.lottonumber.LottoNumber;
import domain.lottonumber.LottoNumberPool;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import static org.assertj.core.api.Assertions.assertThat;

class LottoFactoryTest {
    Set<LottoNumber> sixNumbers;

    @BeforeEach
    void setUp() {
        sixNumbers = new TreeSet<>(Arrays.asList(LottoNumberPool.pickLottoNumber(1),
                LottoNumberPool.pickLottoNumber(2), LottoNumberPool.pickLottoNumber(3),
                LottoNumberPool.pickLottoNumber(4), LottoNumberPool.pickLottoNumber(5),
                LottoNumberPool.pickLottoNumber(6)));
    }

    @Test
    void 입력받은_금액만큼_자동_로또를_제대로_발행해주는지_테스트() {
        PurchaseAmount purchaseAmount = PurchaseAmount.of(5000);
        List<IssuedLotto> issuedLottos = LottoFactory.autoIssueLottoWorthOf(purchaseAmount);

        assertThat(issuedLottos.size()).isEqualTo(5);
    }

    @Test
    void 당첨_로또를_제대로_발행해주는지_테스트() {
        LottoNumber bonusNumber = LottoNumberPool.pickLottoNumber(7);
        WinningLotto expectedWinningLotto = new WinningLotto(sixNumbers, bonusNumber);

        assertThat(LottoFactory.issueWinningLotto(Arrays.asList(1, 2, 3, 4, 5, 6), bonusNumber))
                .isEqualTo(expectedWinningLotto);
    }

    @Test
    void 수동으로_구매한_로또를_제대로_발행해주는지_테스트() {
        Lotto expectedLotto = new IssuedLotto(sixNumbers);

        assertThat(LottoFactory.manualIssueLotto(Arrays.asList(1, 2, 3, 4, 5, 6))).isEqualTo(expectedLotto);
    }
}