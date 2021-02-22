package lotto.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class LottoRankTest {

    @ParameterizedTest
    @DisplayName("로또 맞힌 갯수와 랭크가 일치하는 지 테스트")
    @CsvSource(value = {"6:false:FIRST", "5:true:SECOND", "5:false:THIRD", "4:false:FOURTH",
            "3:false:FIFTH", "2:false:NONE", "1:false:NONE", "0:false:NONE"}, delimiter = ':')
    void test(int matchCount, boolean bonusMatch, LottoRank expectedRank) {
        LottoRank rank = LottoRank.of(matchCount, bonusMatch);
        Assertions.assertThat(rank).isEqualTo(expectedRank);
    }
}