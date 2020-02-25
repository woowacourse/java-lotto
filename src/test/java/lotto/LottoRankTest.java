package lotto;

import domain.LottoRank;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoRankTest {
    @ParameterizedTest
    @CsvSource({"6,false,FIRST", "5,true,SECOND", "5,false,THIRD", "4,false,FOURTH", "3,false,FIFTH"})
    void 로또_등수_확인(int winningBalls, boolean isBonusMatch, LottoRank lottoRank){
        assertThat(LottoRank.findRank(winningBalls, isBonusMatch)).isEqualTo(lottoRank);
    }
}
