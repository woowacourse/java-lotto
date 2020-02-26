package domain.lotto.lottoresult;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoRankTest {
    @Test
    @DisplayName("랭크 반환 테스트")
    void test1() {
        LottoRank lottoRank = LottoRank.calculateRank(3, false);
        Assertions.assertThat(lottoRank).isEqualTo(LottoRank.FIFTH);

        lottoRank = LottoRank.calculateRank(3, true);
        Assertions.assertThat(lottoRank).isEqualTo(LottoRank.FIFTH);

        lottoRank = LottoRank.calculateRank(4, false);
        Assertions.assertThat(lottoRank).isEqualTo(LottoRank.FOURTH);

        lottoRank = LottoRank.calculateRank(4, true);
        Assertions.assertThat(lottoRank).isEqualTo(LottoRank.FOURTH);

        lottoRank = LottoRank.calculateRank(5, false);
        Assertions.assertThat(lottoRank).isEqualTo(LottoRank.THIRD);

        lottoRank = LottoRank.calculateRank(5, true);
        Assertions.assertThat(lottoRank).isEqualTo(LottoRank.SECOND);

        lottoRank = LottoRank.calculateRank(6, false);
        Assertions.assertThat(lottoRank).isEqualTo(LottoRank.FIRST);

        lottoRank = LottoRank.calculateRank(6, true);
        Assertions.assertThat(lottoRank).isEqualTo(LottoRank.FIRST);

        lottoRank = LottoRank.calculateRank(2, true);
        Assertions.assertThat(lottoRank).isEqualTo(LottoRank.NOTHING);
    }

    @Test
    @DisplayName("ResultCount 곱셈 확인")
    void test2() {
        Assertions.assertThat(LottoRank.FIRST.multiplyCount(new ResultCount(1))).isEqualTo(2_000_000_000);
        Assertions.assertThat(LottoRank.SECOND.multiplyCount(new ResultCount(2))).isEqualTo(60_000_000);
        Assertions.assertThat(LottoRank.THIRD.multiplyCount(new ResultCount(2))).isEqualTo(3_000_000);
        Assertions.assertThat(LottoRank.FOURTH.multiplyCount(new ResultCount(2))).isEqualTo(100_000);
        Assertions.assertThat(LottoRank.FIFTH.multiplyCount(new ResultCount(2))).isEqualTo(10_000);
        Assertions.assertThat(LottoRank.NOTHING.multiplyCount(new ResultCount(2))).isEqualTo(0);
    }
}
