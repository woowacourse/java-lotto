package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class RankTest {
    @DisplayName("매치 카운트랑 보너스 카운트에 맞는 합당한 결과를 알려 주는지")
    @Test
    void getResult() {
        int matchCount = 0;
        boolean bonusMatch = true;

        assertThat(Rank.getCorrespondingRank(matchCount, bonusMatch)).isEqualTo(Rank.NONE);
    }
}
