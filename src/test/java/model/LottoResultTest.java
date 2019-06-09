package model;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.offset;

class LottoResultTest {
    @Test
    void earningRateTest() {
        Lotto a = new Lotto("11, 17, 19, 21, 22, 24");
        Lotto b = new Lotto("11, 17, 19, 30, 31, 32");
        Lotto c = new Lotto("10, 20, 30, 40, 44, 45");
        LottoResult result = new LottoResult(Arrays.asList(a, b, c), new AutoWinningNumbers());
        assertThat(result.earningRate()).isCloseTo(
                (((LottoRank.SECOND.prize().amount() + LottoRank.FIFTH.prize().amount()) / 3000.0) - 1.0) * 100.0,
                offset(0.0000001)
        );
    }
}