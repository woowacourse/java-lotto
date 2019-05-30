package model;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.offset;

class LottoResultTest {
    @Test
    void earningRateTest() {
        Lotto a = new Lotto("4, 8, 18, 25, 27, 42");
        Lotto b = new Lotto("4, 8, 18, 14, 5, 16");
        Lotto c = new Lotto("10, 20, 30, 40, 44, 45");
        LottoResult result = new LottoResult(Arrays.asList(a, b, c));
        assertThat(result.getEarningRate()).isCloseTo(
                (((LottoRank.SECOND.getPrize().getAmount() + LottoRank.FIFTH.getPrize().getAmount()) / 3000.0) - 1.0) * 100.0,
                offset(0.0000001)
        );
    }
}