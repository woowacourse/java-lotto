package domain.lottoresult;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class LottoRankTest {
    @Test
    void 랭크_반환_테스트() {
        for (int hit = 0; hit <= 6; hit++) {
            LottoRank lottoRank = LottoRank.calculateRank(hit, false);
            Assertions.assertThat(lottoRank).isEqualTo(LottoRank.calculateRank(hit, false));

            lottoRank = LottoRank.calculateRank(hit, true);
            Assertions.assertThat(lottoRank).isEqualTo(LottoRank.calculateRank(hit, true));
        }
    }
}
