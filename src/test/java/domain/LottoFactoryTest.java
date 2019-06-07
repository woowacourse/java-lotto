package domain;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import static org.assertj.core.api.Assertions.assertThat;

class LottoFactoryTest {
    @Test
    void 입력받은_금액만큼_로또를_제대로_발행해주는지_테스트() {
        PurchaseAmount purchaseAmount = PurchaseAmount.of(5000);
        List<IssuedLotto> issuedLottos = LottoFactory.issueLottoWorthOf(purchaseAmount);

        assertThat(issuedLottos.size()).isEqualTo(5);
    }

    @Test
    void 당첨_로또를_제대로_발행해주는지_테스트() {
        Set<LottoNumber> winningNumbers = new TreeSet<>(Arrays.asList(new LottoNumber(1),
                new LottoNumber(2), new LottoNumber(3), new LottoNumber(4),
                new LottoNumber(5), new LottoNumber(6)));
        LottoNumber bonusNumber = new LottoNumber(7);
        WinningLotto expectedWinningLotto = new WinningLotto(winningNumbers, bonusNumber);

        assertThat(LottoFactory.issueWinningLotto(Arrays.asList(1, 2, 3, 4, 5, 6), bonusNumber))
                .isEqualTo(expectedWinningLotto);
    }
}