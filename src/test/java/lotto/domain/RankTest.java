package lotto.domain;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

public class RankTest {
    @ParameterizedTest
    @CsvSource(value = {
            "5,false,THIRD",
            "5,true,SECOND",
    })
    void 등수가_맞게_나오는지_확인(int match, boolean bonusMatch, Rank expected) {
        Rank rank = Rank.makeRankByMatch(match, bonusMatch);
        assertThat(rank).isEqualTo(expected);
    }
}
