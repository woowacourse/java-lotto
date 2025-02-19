package domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.EnumMap;
import java.util.Map;
import org.junit.jupiter.api.Test;

class WinningNumbersTest {

    @Test
    void 수익률을_계산한다() {
        Map<Rank, Integer> inputResult = new EnumMap<>(Map.ofEntries(Map.entry(Rank.THREE, 2)));
        int purchaseAmount = 1000;
        double expectedYield = 10.00;

        assertThat(WinningNumbers.calculateYield(inputResult, purchaseAmount)).isEqualTo(expectedYield);
    }
}