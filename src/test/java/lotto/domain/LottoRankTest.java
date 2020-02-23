package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoRankTest {

    @DisplayName("랭크 잘 가져오는지 확인 - 2등 3등일 경우에만 분기")
    @Test
    void getRank() {
        assertThat(LottoRank.getRank(5, false)).isEqualTo(LottoRank.THIRD);
        assertThat(LottoRank.getRank(5, true)).isEqualTo(LottoRank.SECOND);
        assertThat(LottoRank.getRank(6, false)).isEqualTo(LottoRank.FIRST);
        assertThat(LottoRank.getRank(6, true)).isEqualTo(LottoRank.FIRST);
    }
}
