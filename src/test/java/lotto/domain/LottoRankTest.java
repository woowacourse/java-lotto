package lotto.domain;

import static org.junit.jupiter.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class LottoRankTest {

    @ParameterizedTest
    @DisplayName("등수를 올바르게 결정하는지 확인")
    @CsvSource(value = {"6:false:FIRST", "5:true:SECOND", "5:false:THIRD", "4:false:FOURTH",
            "4:true:FOURTH", "3:true:FIFTH", "3:false:FIFTH"}, delimiter = ':')
    void valueOf(int sameCount, boolean bonus, LottoRank expectedLottoRank) {
        LottoRank lottoRank = LottoRank.valueOf(sameCount, bonus);
        Assertions.assertThat(lottoRank).isEqualTo(expectedLottoRank);
    }
}
