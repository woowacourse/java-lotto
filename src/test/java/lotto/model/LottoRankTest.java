package lotto.model;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoRankTest {
        @Test
        void 당첨금_추출_검사() {
                assertThat(LottoRank.getPrizes(5, true)).isEqualTo(30_000_000);
        }

        @Test
        void 등수_추출_검사() {
                assertThat(LottoRank.getLottoRank(5, true)).isEqualTo(LottoRank.SECOND);
        }
}
