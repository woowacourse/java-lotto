package lotto.type;

import lotto.domain.ticketresult.Rank;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class RankTest {
    @DisplayName("matchedNumberCount를 통해 Rank 찾기 - 5등 미만")
    @Test
    void Should_Return_None_When_CountMatchedNumbers_Zero() {
        Rank rank = Rank.getLottoRank(0, false);
        Assertions.assertThat(rank).isSameAs(Rank.NONE);
    }

    @DisplayName("matchedNumberCount를 통해 Rank 찾기 - 5등 미만")
    @Test
    void Should_Return_None_When_CountMatchedNumbers_One() {
        Rank rank = Rank.getLottoRank(1, false);
        Assertions.assertThat(rank).isSameAs(Rank.NONE);
    }

    @DisplayName("matchedNumberCount를 통해 Rank 찾기 - 5등 미만")
    @Test
    void Should_Return_None_When_CountMatchedNumbers_Two() {
        Rank rank = Rank.getLottoRank(2, false);
        Assertions.assertThat(rank).isSameAs(Rank.NONE);
    }

    @DisplayName("matchedNumberCount를 통해 Rank 찾기 - 5등")
    @Test
    void Should_Return_Fifth_When_CountMatchedNumbers_Three() {
        Rank rank = Rank.getLottoRank(3, false);
        Assertions.assertThat(rank).isSameAs(Rank.FIFTH);
    }

    @DisplayName("matchedNumberCount를 통해 Rank 찾기 - 4등")
    @Test
    void Should_Return_Fourth_When_CountMatchedNumbers_Four() {
        Rank rank = Rank.getLottoRank(4, false);
        Assertions.assertThat(rank).isSameAs(Rank.FOURTH);
    }

    @DisplayName("matchedNumberCount를 통해 Rank 찾기 - 3등")
    @Test
    void Should_Return_Third_When_CountMatchedNumbers_Five() {
        Rank rank = Rank.getLottoRank(5, false);
        Assertions.assertThat(rank).isSameAs(Rank.THIRD);
    }

    @DisplayName("matchedNumberCount를 통해 Rank 찾기 - 2등")
    @Test
    void Should_Return_Second_When_CountMatchedNumbers_FiveAndBonus() {
        Rank rank = Rank.getLottoRank(5, true);
        Assertions.assertThat(rank).isSameAs(Rank.SECOND);
    }

    @DisplayName("matchedNumberCount를 통해 Rank 찾기 - 3등")
    @Test
    void Should_Return_First_When_CountMatchedNumbers_Six() {
        Rank rank = Rank.getLottoRank(6, false);
        Assertions.assertThat(rank).isSameAs(Rank.FIRST);
    }
}
