package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class RankingTest {

    @ParameterizedTest
    @CsvSource(value = {"6,false,FIRST_PLACE",
            "5,true,SECOND_PLACE",
            "5,false,THIRD_PLACE",
            "4,true,FOURTH_PLACE",
            "4,false,FOURTH_PLACE",
            "3,true,FIFTH_PLACE",
            "3,false,FIFTH_PLACE",
            "2,true,NONE_PLACE",
            "2,false,NONE_PLACE"})
    @DisplayName("맞은 개수와 보너스볼 유무로 일치하는 순위를 반환")
    void findRanking(int hitCount, boolean hasBonusNumber, Ranking expectedRanking) {
        assertThat(Ranking.of(hitCount, hasBonusNumber)).isEqualTo(expectedRanking);
    }

    @ParameterizedTest
    @CsvSource(value = {"FIRST_PLACE,1,2000000000",
            "SECOND_PLACE,2,60000000",
            "THIRD_PLACE,3,4500000",
            "FOURTH_PLACE,4,200000",
            "FIFTH_PLACE,5,25000"})
    @DisplayName("등수에 해당하는 상금과 파라미터의 횟수 곱 테스트")
    void multiplyPrizeWithCount(Ranking ranking, int count, long expected) {
        assertThat(ranking.multiplyPrizeWithCount(count)).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource(value = {"FIRST_PLACE,false",
            "SECOND_PLACE,true",
            "THIRD_PLACE,false",
            "FOURTH_PLACE,false",
            "FIFTH_PLACE,false"})
    @DisplayName("SECOND_PLACE 라면 true 아니면 false 반환")
    void multiplyPrizeWithCount(Ranking ranking, boolean expected) {
        assertThat(ranking.isSecond()).isEqualTo(expected);
    }
}
