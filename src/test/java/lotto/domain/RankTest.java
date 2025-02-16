package lotto.domain;

import static lotto.domain.Rank.FIRST_PRIDE;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.EnumMap;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class RankTest {

    @DisplayName("당첨번호와 보너스번호로 당첨내용을 확인한다")
    @ParameterizedTest
    @CsvSource(value = {"6:false:FIRST_PRIDE",
            "5:true:SECOND_PRIDE",
            "5:false:THIRD_PRIDE",
            "4:false:FOURTH_PRIDE",
            "3:false:FIFTH_PRIDE",
            "2:false:BOOM"
    }, delimiterString = ":")
    void 당첨번호와_보너스번호로_당첨내용을_확인한다(int matchCount, boolean matchBonus, Rank rank) {

        //given
        Rank currentRank = Rank.checkPrize(matchCount, matchBonus);

        //when & then
        assertThat(currentRank).isEqualTo(rank);
    }

    @DisplayName("기본 맵을 생성한다.")
    @Test
    void 기본_맵을_생성한다() {

        //given
        EnumMap<Rank, Integer> defaultMap = Rank.makeDefaultMap();

        //when & then
        assertThat(defaultMap.size()).isEqualTo(6);
        assertThat(defaultMap.get(FIRST_PRIDE)).isEqualTo(0);
    }

    @DisplayName("로또 결과에 따른 당첨 금액을 반환한다")
    @Test
    void 로또_결과에_따른_당첨_금액을_반환한다() {

        //given
        EnumMap<Rank, Integer> lottoResult = new EnumMap<>(Rank.class);
        lottoResult.put(FIRST_PRIDE, 1);

        //when
        int prize = Rank.calculateTotalPrize(lottoResult);

        //then
        assertThat(prize).isEqualTo(FIRST_PRIDE.getPrize());

    }
}
