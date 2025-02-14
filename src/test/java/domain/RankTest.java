package domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class RankTest {

    @ParameterizedTest
    @CsvSource({
            "FIFTH, 3, false",
            "FOURTH, 4, false",
            "THIRD, 5, false",
            "SECOND, 5, true",
            "FIRST, 6, false",
            "MISS, 0, false",
            "MISS, 1, false",
            "MISS, 2, false",
            "MISS, 2, true",
    })
    void 당첨결과에_해당하는_순위를_찾을_수_있다(Rank expected, int matchCount, boolean isRequireBonus) {
        //when
        Rank actual = Rank.findRank(matchCount, isRequireBonus);
        //then
        Assertions.assertThat(actual).isEqualTo(expected);
    }

    @Test
    void 당첨금을_계산할_수_있다() {
        //given
        int count = 3;

        //when
        Money money = Rank.FIFTH.calculateWinningMoney(count);

        //then
        Assertions.assertThat(money).isEqualTo(new Money(15000));
    }

}
