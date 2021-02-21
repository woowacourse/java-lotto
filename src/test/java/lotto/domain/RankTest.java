package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

public class RankTest {
    @ParameterizedTest
    @CsvSource(value = {
            "5,false,THIRD",
            "5,true,SECOND",
    })
    @DisplayName("등수가 맞게 나오는지 확인")
    void isRightRank(int match, boolean bonusMatch, Rank expected) {
        Rank rank = Rank.makeRankByMatch(match, bonusMatch);
        assertThat(rank).isEqualTo(expected);
    }
}
