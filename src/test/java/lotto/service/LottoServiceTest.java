package lotto.service;

import lotto.domain.LottoMatchKind;
import lotto.domain.generator.CustomLottoGenerator;
import lotto.domain.generator.Generator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static lotto.domain.LottoMatchKind.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.entry;

class LottoServiceTest {
    private final Generator customLottoGenerator = new CustomLottoGenerator();
    private final String purchaseAmount = "5000";
    private final LottoService lottoService = new LottoService(customLottoGenerator, purchaseAmount);
    private final List<String> targetNumbers = Arrays.asList("2", "3", "4", "5", "6", "7");
    private final String bonusNumber = "1";

    @Test
    @DisplayName("구매 개수를 반환한다.")
    void getCountOfLottoNumbers() {
        //
        final int expected = 5;
        //when
        final int actual = lottoService.getCountOfLottoNumbers();
        //then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("당첨 결과를 반환한다.")
    void getMatchResult() {
        final Map<LottoMatchKind, Integer> actual = lottoService.getMatchResult(targetNumbers, bonusNumber);
        assertThat(actual).containsExactly(
                entry(THREE, 1), entry(FOUR, 1), entry(FIVE, 1), entry(FIVE_BONUS, 1), entry(SIX, 1));
    }

    @Test
    @DisplayName("수익률을 반환한다.")
    void getProfitRate() {
        //given
        lottoService.getMatchResult(targetNumbers, bonusNumber);
        final double expected = 2031555000 / (double) 5000;
        //when
        final double actual = lottoService.getProfitRate();
        //then
        assertThat(actual).isEqualTo(expected);
    }
}
