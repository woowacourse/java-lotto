package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@Nested
@DisplayName("matchRank 메소드는")
class RankTest {
    @Nested
    @DisplayName("일치하는 수의 개수와 보너스번호 일치 여부를 입력받으면")
    class if_input_matchCount_and_hasBonusNumber {
        @Test
        @DisplayName("해당하는 등수를 출력한다.")
        void returns_rank() {
            assertThat(Rank.matchRank(6,false) == Rank.FIRST).isTrue();
            assertThat(Rank.matchRank(5,true) == Rank.SECOND).isTrue();
            assertThat(Rank.matchRank(5,false) == Rank.THIRD).isTrue();
            assertThat(Rank.matchRank(4,false) == Rank.FOURTH).isTrue();
            assertThat(Rank.matchRank(3,false) == Rank.FIFTH).isTrue();
            assertThat(Rank.matchRank(1,false) == Rank.NO_MATCH).isTrue();
        }
    }
}
