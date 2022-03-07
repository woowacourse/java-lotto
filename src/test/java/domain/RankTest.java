package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class RankTest {
    @Test
    @DisplayName("hitCount가 5, hitBonusBall이 True일 때 2등 검사에서 True를 반환한다.")
    public void isSecondRankTest() {
        int hitCount = 5;
        boolean isHitBonusBall = true;
        boolean actual = Rank.isSecondRank(hitCount, isHitBonusBall);
        assertThat(actual).isTrue();
    }

    @Test
    @DisplayName("Result에 따라 Rank를 맞게 매칭하는지 확인한다.")
    public void judgeResultTest_FIRST() {
        int hitCount = 6;
        boolean bonusBall = false;

        Rank actual = Rank.judgeResult(hitCount, bonusBall);

        assertThat(actual).isEqualTo(Rank.FIRST);
    }

    @Test
    @DisplayName("Result에 따라 Rank를 맞게 매칭하는지 확인한다.")
    public void judgeResultTest_NONE() {
        int hitCount = 2;
        boolean bonusBall = false;

        Rank actual = Rank.judgeResult(hitCount, bonusBall);

        assertThat(actual).isEqualTo(Rank.NONE);
    }
}
