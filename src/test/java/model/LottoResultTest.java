package model;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.offset;

class LottoResultTest {
    @Test
    void earningRateTest() {
        Lotto a = new Lotto("1, 2, 3, 4, 5, 6");
        Lotto b = new Lotto("10, 34, 38, 30, 31, 33");
        Lotto c = new Lotto("10, 34, 38, 40, 42, 32");
        LottoResult result = new LottoResult(Arrays.asList(a, b, c), WinningNumbersFactory.of(862));
        assertThat(
                result.earningRate()
        ).isCloseTo(
                ((LottoRank.SECOND.prize().amount() + LottoRank.FIFTH.prize().amount()) / (3.0 * Lotto.PRICE) - 1.0) * 100.0,
                offset(0.0000001)
        );
    }
}