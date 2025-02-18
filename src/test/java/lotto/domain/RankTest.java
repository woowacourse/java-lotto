package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.EnumMap;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class RankTest {

    @DisplayName("로또의 당첨순위를 계산한다.")
    @ParameterizedTest
    @CsvSource(value = {"6:false:FIRST_PRIDE", "5:true:SECOND_PRIDE", "5:false:THIRD_PRIDE", "4:false:FOURTH_PRIDE",
            "3:false:FIFTH_PRIDE", "2:false:BOOM"}, delimiterString = ":")
    void 로또의_당첨순위를_계산한다(int matchCount, boolean matchBonus, Rank rank) {
        Rank currentRank = Rank.checkPrize(matchCount, matchBonus);

        assertThat(currentRank).isEqualTo(rank);
    }

    @DisplayName("각 당첨순위의 당첨횟수가 0인 초기 결과를 생성한다.")
    @Test
    void 각_당첨순위의_당첨횟수가_0인_초기결과를_생성한다() {
        EnumMap<Rank, Integer> defaultMap = Rank.makeDefaultMap();

        assertThat(defaultMap.size()).isEqualTo(6);
        assertThat(defaultMap.get(Rank.FIRST_PRIDE)).isEqualTo(0);
    }

    @DisplayName("로또뭉치의 전체 당첨금을 계산한다.")
    @Test
    void 로또뭉치의_전체_당첨금을_계산한다() {
        EnumMap<Rank, Integer> defaultMap = Rank.makeDefaultMap();
        defaultMap.put(Rank.FOURTH_PRIDE, 1);
        defaultMap.put(Rank.FIFTH_PRIDE, 2);

        assertThat(Rank.calculateTotalPrize(defaultMap)).isEqualTo(60000);
    }
}
