package lotto.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class RankTest {

    @Test
    void 두번째_등수_확인() {
        //given
        int matchCounts = 5;
        boolean matchBonus = true;

        //when & then
        Assertions.assertThat(Rank.checkRank(matchCounts,matchBonus)).isEqualTo(Rank.SECOND);
    }

    @Test
    void 세번째_등수_확인() {
        //given
        int matchCounts = 5;
        boolean matchBonus = false;

        //when & then
        Assertions.assertThat(Rank.checkRank(matchCounts,matchBonus)).isEqualTo(Rank.THIRD);
    }

    @Test
    void 첫번째_등수_확인() {
        //given
        int matchCounts = 6;
        boolean matchBonus = false;

        //when & then
        Assertions.assertThat(Rank.checkRank(matchCounts,matchBonus)).isEqualTo(Rank.FIRST);
    }

    @Test
    void 네번째_등수_확인() {
        //given
        int matchCounts = 4;
        boolean matchBonus = false;

        //when & then
        Assertions.assertThat(Rank.checkRank(matchCounts,matchBonus)).isEqualTo(Rank.FOURTH);
    }

    @Test
    void 미당첨_등수_확인() {
        //given
        int matchCounts = 1;
        boolean matchBonus = false;

        //when & then
        Assertions.assertThat(Rank.checkRank(matchCounts,matchBonus)).isEqualTo(Rank.NONE);
    }
}
