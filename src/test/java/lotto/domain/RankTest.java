package lotto.domain;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class RankTest {
    @Test
    void matching_개수에_따라_해당_등수의_Rank를_반환() {
        assertThat(Rank.valueOf(0)).isEqualByComparingTo(Rank.MISS);
        assertThat(Rank.valueOf(1)).isEqualByComparingTo(Rank.MISS);
        assertThat(Rank.valueOf(3)).isEqualByComparingTo(Rank.FIFTH);
        assertThat(Rank.valueOf(4)).isEqualByComparingTo(Rank.FOURTH);
        assertThat(Rank.valueOf(5)).isEqualByComparingTo(Rank.THIRD);
        assertThat(Rank.valueOf(6)).isEqualByComparingTo(Rank.FIRST);
    }

    @Test
    void MISS를_제외한_Rank의_list_반환() {
        List<Rank> ranks = Rank.valuesWithoutMISS();
        assertThat(ranks.contains(Rank.MISS)).isFalse();
    }

    @Test
    void 파라미터의_횟수를_기준으로_해당_Rank의_상금을_계산해서_반환() {
        assertThat(Rank.FIFTH.calculatePrize(3)).isEqualTo(15_000);
    }

    @Test
    void numOfMatching_반환() {
        assertThat(Rank.FIFTH.getNumOfMatching()).isEqualTo(3);
    }

    @Test
    void prize_반환() {
        assertThat(Rank.FIFTH.getPrize()).isEqualTo(5_000);
    }
}