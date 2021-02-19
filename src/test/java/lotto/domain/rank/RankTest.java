package lotto.domain.rank;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
class RankTest {

    @ParameterizedTest
    @DisplayName("매칭된 번호와 보너스 번호의 개수에 맞는 등수 반환")
    @CsvSource({"6,false,FIRST", "5,true,SECOND", "5,false,THIRD"})
    void getRank(int matchCount, boolean bonusNumber, Rank rank) {
        assertThat(Rank.getRank(matchCount, bonusNumber)).isEqualTo(rank);
    }
}