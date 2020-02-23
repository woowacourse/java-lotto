package lotto.domain.result.rank;

import lotto.domain.result.LottoResult;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

//TODO: findrankByLottoResult
class RankTest {
    @DisplayName("로또 결과에 따른 상금정보 찾기")
    @ParameterizedTest
    @CsvSource(value = {
            "6,false,FIRST",
            "6,true,FIRST",
            "5,true,SECOND",
            "5,false,THIRD",
            "4,false,FOURTH",
            "4,true,FOURTH",
            "3,false,FIFTH",
            "3,true,FIFTH",
            "2,false,SIXTH",
            "2,true,SIXTH",
            "1,false,SIXTH",
            "0,false,SIXTH",
    })
    void name(int matchCount, boolean isBonusMatch, Rank expected) {
        //given
        LottoResult result = new LottoResult(matchCount, isBonusMatch);
        //when
        Rank rank = Rank.findRankByLottoResult(result);
        //then
        assertThat(rank).isEqualTo(expected);
    }
}
