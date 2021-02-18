package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class LottoRankTest {

    @DisplayName("당첨 로또 등수를 반환하는 기능")
    @ParameterizedTest
    @CsvSource(value = {
            "6:false:FIRST", "5:true:SECOND", "5:false:THIRD", "4:false:FOURTH", "3:false:FIFTH", "0:false:MISS"
    }, delimiter = ':')
    void isMatch(int correctCnt, boolean isBonusMatch, LottoRank result) {
        //when
        LottoRank lottoRank = LottoRank.valueOf(correctCnt, isBonusMatch);

        //then
        assertThat(lottoRank).isEqualTo(result);
    }
}
