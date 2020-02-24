package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import lotto.domain.result.Rank;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Arrays;

class RankTest {
    @Test
    @DisplayName("맞힌 수와 매칭되는 Enum을 반환")
    void getRankTest() {
        Assertions.assertThat(Rank.getRank(3, false)).isEqualTo(Rank.THREE);
        assertThat(Rank.getRank(4, false)).isEqualTo(Rank.FOUR);
    }

    @ParameterizedTest
    @CsvSource(value = {"5,false,1500000", "4,false,50000", "5,true,30000000"})
    @DisplayName("상금을 정상적으로 반환하는 지")
    void profitTest(int match, boolean bonusMatch, int profit) {
        Rank rank = Rank.getRank(match, bonusMatch);
        assertThat(rank.getPrize()).isEqualTo(profit);
    }

    @Test
    void getMatchingNumberTest() {
        assertThat(Rank.BONUS.getMatchingNumbers()).isEqualTo(5);
    }
}