package lotto.domain;

import static org.assertj.core.api.Assertions.within;

import java.util.EnumMap;
import java.util.Map;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class WinningResultTest {

    @Test
    void 당첨_결과가_정상적으로_생성된다() {
        // Given
        Map<LottoAward, Integer> winningResult = new EnumMap<>(LottoAward.class);

        // When & Then
        Assertions.assertThatCode(() -> new WinningResult(winningResult))
                .doesNotThrowAnyException();
    }

    @Test
    void 수익률을_계산한다() {
        // Given
        LottoPrice purchasedPrice = new LottoPrice(14_000);
        Map<LottoAward, Integer> expectedResult = Map.of(LottoAward.FIRST_RANK, 0, LottoAward.SECOND_RANK, 0,
                LottoAward.THIRD_RANK, 0, LottoAward.FOURTH_RANK, 0, LottoAward.FIFTH_RANK, 1);
        WinningResult winningResult = new WinningResult(expectedResult);

        // When & Then
        Assertions.assertThat(winningResult.calculateProfitRate(purchasedPrice)).isCloseTo(0.35, within(0.01));
    }
}
