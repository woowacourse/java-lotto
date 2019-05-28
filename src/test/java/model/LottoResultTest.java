package model;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.offset;
import static org.junit.jupiter.api.Assertions.*;

class LottoResultTest {
    @Test
    void earningRateTest() {
        Lotto a = new Lotto("1, 2, 3, 4, 5, 6");
        Lotto b = new Lotto("1, 12, 3, 14, 5, 16");
        Lotto c = new Lotto("10, 20, 30, 40, 44, 45");
        Set<LottoNumber> winningNumbers = new HashSet<LottoNumber>() {{
            add(LottoNumber.of(1));
            add(LottoNumber.of(2));
            add(LottoNumber.of(3));
            add(LottoNumber.of(4));
            add(LottoNumber.of(5));
            add(LottoNumber.of(11));
        }};
        LottoResult result = new LottoResult(Arrays.asList(a, b, c), winningNumbers, LottoNumber.of(6));
        assertThat(result.getEarningRate()).isCloseTo(
                (((LottoRank.SECOND.getPrize().getAmount() + LottoRank.FIFTH.getPrize().getAmount()) / 3000.0) - 1.0) * 100.0,
                offset(0.0000001)
        );
    }
}