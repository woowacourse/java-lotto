package lotto.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class RankTest {

    @Test
    public void 등수_비교_테스트() {
        assertThat(Rank.matchRank(6,false) == Rank.FIRST).isTrue();
        assertThat(Rank.matchRank(5,true) == Rank.SECOND).isTrue();
        assertThat(Rank.matchRank(5,false) == Rank.THIRD).isTrue();
        assertThat(Rank.matchRank(4,false) == Rank.FOURTH).isTrue();
        assertThat(Rank.matchRank(3,false) == Rank.FIFTH).isTrue();
        assertThat(Rank.matchRank(1,false) == Rank.NO_MATCH).isTrue();
    }

}
