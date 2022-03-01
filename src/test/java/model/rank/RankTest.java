package model.rank;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class RankTest {

    @ParameterizedTest
    @CsvSource(value = {"3,true", "3,false"}, delimiter = ',')
    @DisplayName("일치하는 당첨번호가 3개이면 보너스볼 번호 일치와 상관없이 5등을 반환한다.")
    void findRank_Fifth(int matchCount, boolean hasBonus) {
        assertThat(Rank.valueOf(matchCount, hasBonus)).isEqualTo(Rank.FIFTH);
    }

    @ParameterizedTest
    @CsvSource(value = {"4,true", "4,false"}, delimiter = ',')
    @DisplayName("일치하는 당첨번호가 4개이면 보너스볼 번호 일치와 상관없이 4등을 반환한다.")
    void findRank_Fourth(int matchCount, boolean hasBonus) {
        assertThat(Rank.valueOf(matchCount, hasBonus)).isEqualTo(Rank.FOURTH);
    }

    @ParameterizedTest
    @CsvSource(value = {"5,false"}, delimiter = ',')
    @DisplayName("일치하는 당첨번호가 5개이고 보너스 볼이 일치하지 않으면 3등을 반환한다.")
    void findRank_Third(int matchCount, boolean hasBonus) {
        assertThat(Rank.valueOf(matchCount, hasBonus)).isEqualTo(Rank.THIRD);
    }

    @ParameterizedTest
    @CsvSource(value = {"5,true"}, delimiter = ',')
    @DisplayName("일치하는 당첨번호가 5개이고 보너스 볼이 일치하면 2등을 반환한다.")
    void findRank_Second(int matchCount, boolean hasBonus) {
        assertThat(Rank.valueOf(matchCount, hasBonus)).isEqualTo(Rank.SECOND);
    }

    @ParameterizedTest
    @CsvSource(value = {"6,false"}, delimiter = ',')
    @DisplayName("일치하는 당첨번호가 6개이면 1등을 반환한다.")
    void findRank_First(int matchCount, boolean hasBonus) {
        assertThat(Rank.valueOf(matchCount, hasBonus)).isEqualTo(Rank.FIRST);
    }
}