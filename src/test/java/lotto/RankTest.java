package lotto;

import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class RankTest {

    @Test
    @DisplayName("매칭한 숫자가 6이면 First를 반환한다.")
    void matchCountToRankFirst() {
        assertThat(Rank.find(6, false)).isEqualTo(Rank.FIRST);
    }

    @Test
    @DisplayName("매칭한 숫자가 5이면 Third를 반환한다.")
    void matchCountToRankThird() {
        assertThat(Rank.find(5, false)).isEqualTo(Rank.THIRD);
    }

    @Test
    @DisplayName("매칭한 숫자가 6이면 Second를 반환한다.")
    void matchCountToRankSecond() {
        assertThat(Rank.find(5, true)).isEqualTo(Rank.SECOND);
    }

    @ParameterizedTest
    @CsvSource(value = {"0,false", "0,true", "1,false", "1,true", "2,false"})
    @DisplayName("매칭한 숫자가 이면 Second를 반환한다.")
    void matchCountToRankNone(int matchCount, boolean matchBonus) {
        assertThat(Rank.find(matchCount, matchBonus)).isEqualTo(Rank.NONE);
    }
}
