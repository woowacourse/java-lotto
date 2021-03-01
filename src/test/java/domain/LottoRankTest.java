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
        LottoRank lottoRank = LottoRank.isMatch(correctCnt, isBonusMatch);

        //then
        assertThat(lottoRank == result).isTrue();
    }

    @DisplayName("특정 등수의 총 금액을 계산하는 기능")
    @CsvSource(value = {
            "6:FIRST", "5:SECOND", "5:THIRD", "4:FOURTH", "3:FIFTH", "0:MISS"
    }, delimiter = ':')
    void getTotalPrize(long countOfLotto, LottoRank result) {
        //when
        Money totalPrize = result.calculateTotalPrize(countOfLotto);

        //then
        Money expected = result.getPrize().multiply(countOfLotto);
        assertThat(totalPrize).isEqualTo(expected);
    }
}
