package lotto.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class LottoRankTest {

    @ParameterizedTest
    @DisplayName("올바른 등수를 판별하는지")
    @CsvSource(value = {"6:false:RANK_FIRST", "5:true:RANK_SECOND", "5:false:RANK_THIRD",
            "4:false:RANK_FOURTH", "3:false:RANK_FIFTH"}, delimiter = ':')
    void Decide_Rank(long targetCorrectCount, boolean isTargetBonused, LottoRank inputRank) {
        Assertions.assertThat(LottoRank.findLottoRank(targetCorrectCount, isTargetBonused)).isEqualTo(inputRank);
    }
}
