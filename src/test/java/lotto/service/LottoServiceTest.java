package lotto.service;

import lotto.domain.matchkind.LottoMatchKind;
import lotto.domain.lottonumber.Lotto;
import lotto.domain.lottonumber.WinningNumbers;
import lotto.domain.generator.LottoCustomGenerator;
import lotto.domain.generator.LottoGenerator;
import lotto.domain.lottonumber.vo.LottoNumber;
import lotto.domain.purchaseamount.PurchaseAmount;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static lotto.domain.matchkind.LottoMatchKind.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.entry;

class LottoServiceTest {
    private final LottoGenerator lottoGenerator = new LottoCustomGenerator();
    private final PurchaseAmount purchaseAmount = PurchaseAmount.fromPurchaseAmountAndLottoPrice("6000");
    private final Lotto LottoUserInputLotto = new Lotto(Arrays.asList("40", "41", "42", "43", "44", "45"));
    private final List<Lotto> manualLottos =
            Arrays.asList(LottoUserInputLotto);
    private final LottoService lottoService = new LottoService(lottoGenerator, purchaseAmount, manualLottos);
    private final WinningNumbers winningNumbers = new WinningNumbers(
            new Lotto(Arrays.asList("2", "3", "4", "5", "6", "7")), LottoNumber.from("1"));

    @Test
    @DisplayName("구매 개수를 반환한다.")
    void getCountOfLottoNumbers_Test() {
        final int expected = 6;
        //when
        final int actual = lottoService.getCountOfLottoNumbers();
        //then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("구매한 모든 로또 숫자들을 반환한다.")
    void getLottoNumbersGroup_Test() {
        //given
        final Lotto first = new Lotto(Arrays.asList("1", "2", "3", "4", "5", "6"));
        final Lotto second = new Lotto(Arrays.asList("2", "3", "4", "5", "6", "7"));
        final Lotto third = new Lotto(Arrays.asList("3", "4", "5", "6", "7", "8"));
        final Lotto fourth = new Lotto(Arrays.asList("4", "5", "6", "7", "8", "9"));
        final Lotto fifth = new Lotto(Arrays.asList("5", "6", "7", "8", "9", "10"));
        final List<Lotto> expected = Arrays.asList(LottoUserInputLotto, first, second, third, fourth, fifth);
        //when
        final List<Lotto> actual = lottoService.getLottos();
        //then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("당첨 결과를 반환한다.")
    void getMatchResult_Test() {
        final Map<LottoMatchKind, Integer> actual = lottoService.getMatchResult(winningNumbers)
                .getWinningNumberByKind();
        assertThat(actual).containsExactly(
                entry(LOWER_THAN_THREE, 1), entry(THREE, 1),
                entry(FOUR, 1), entry(FIVE, 1),
                entry(FIVE_BONUS, 1), entry(SIX, 1));
    }

    @Test
    @DisplayName("수익률을 반환한다.")
    void getProfitRate_Test() {
        //given
        final double expected = 2031555000 / (double) 6000;
        //when
        final double actual = lottoService.getMatchResult(winningNumbers)
                .getProfitRate();
        //then
        assertThat(actual).isEqualTo(expected);
    }
}
