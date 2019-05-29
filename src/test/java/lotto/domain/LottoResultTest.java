package lotto.domain;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoResultTest {
    @Test
    void 수익률_확인() {
        LottoResult result = LottoResult.of(Winning.of(Arrays.asList(1,2,3,4,5,6), 7),
                Lottos.of(Arrays.asList(Lotto.of(Arrays.asList(1,2,3,10,11,12)))));
        assertThat(result.yield()).isEqualTo(500);
    }
}