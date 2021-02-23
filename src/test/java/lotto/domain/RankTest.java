package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class RankTest {

    @ParameterizedTest
    @CsvSource(value = {
        "5,false,THIRD",
        "5,true,SECOND",
    })
    @DisplayName("등수를 올바르게 매기는지 확인(2등, 3등)")
    void rankMatch(int match, boolean bonusMatch, Rank expected) {
        Rank rank = Rank.makeRankByMatch(match, bonusMatch);
        assertThat(rank).isEqualTo(expected);
    }
}
