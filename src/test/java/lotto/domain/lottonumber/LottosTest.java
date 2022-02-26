package lotto.domain.lottonumber;

import lotto.domain.WinningResult;
import lotto.domain.generator.LottoCustomGenerator;
import lotto.domain.generator.LottoGenerator;
import lotto.domain.lottonumber.vo.LottoNumber;
import lotto.domain.matchkind.LottoMatchKind;
import lotto.domain.purchaseamount.PurchaseAmount;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class LottosTest {
    private final LottoGenerator lottoGenerator = new LottoCustomGenerator();
    private final Lottos lottos = new Lottos(lottoGenerator.generateLottos(5));
    private final WinningNumbers winningNumbers = new WinningNumbers(
            new Lotto(Arrays.asList("2", "3", "4", "5", "6", "7")), LottoNumber.from("1"));

    @Test
    @DisplayName("구매한 모든 로또 숫자들을 반환한다.")
    void getLottos() {
        final Lotto first = new Lotto(Arrays.asList("1", "2", "3", "4", "5", "6"));
        final Lotto second = new Lotto(Arrays.asList("2", "3", "4", "5", "6", "7"));
        final Lotto third = new Lotto(Arrays.asList("3", "4", "5", "6", "7", "8"));
        final Lotto fourth = new Lotto(Arrays.asList("4", "5", "6", "7", "8", "9"));
        final Lotto fifth = new Lotto(Arrays.asList("5", "6", "7", "8", "9", "10"));
        final List<Lotto> expected = Arrays.asList(first, second, third, fourth, fifth);
        //when
        final List<Lotto> actual = lottos.getLottos();
        //then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("당첨 번호와 구매 금액을 받아 당첨 결과를 반환한다.")
    void getWinningResult() {
        //given
        final PurchaseAmount purchaseAmount = PurchaseAmount.fromPurchaseAmountAndLottoPrice("5000", 1000);
        final Map<LottoMatchKind, Integer> expectedMatchNumberByMatchKind = Map.of(
                LottoMatchKind.LOWER_THAN_THREE, 0,
                LottoMatchKind.THREE, 1,
                LottoMatchKind.FOUR, 1,
                LottoMatchKind.FIVE, 1,
                LottoMatchKind.FIVE_BONUS, 1,
                LottoMatchKind.SIX, 1);
        final double expectedProfitRate = 2031555000 / (double) 5000;
        final WinningResult expected = new WinningResult(expectedMatchNumberByMatchKind, expectedProfitRate);
        //when
        final WinningResult actual = lottos.match(winningNumbers, purchaseAmount);
        //then
        assertThat(actual).isEqualTo(expected);
    }
}
