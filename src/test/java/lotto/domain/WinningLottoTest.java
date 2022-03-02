package lotto.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class WinningLottoTest {

    @Test
    @DisplayName("당첨번호와 일치하는 개수")
    void Count_Same_Number() {
        PickedNumbers pickedNumbers = new PickedNumbers("1,2,3,4,5,6");
        BonusNumber bonusNumber = new BonusNumber("7", pickedNumbers);
        WinningLotto winningLotto = new WinningLotto(pickedNumbers, bonusNumber);
        Lotto lotto = new Lotto(new PickedNumbers("1,2,3,12,13,14"));
        Assertions.assertThat(winningLotto.findSameNumbersInPicked(lotto.getPickedNumbers())).isEqualTo(3);
    }


    @ParameterizedTest
    @DisplayName("올바른 등수를 판별하는지")
    @CsvSource(value = {"6:false:RANK_FIRST", "5:true:RANK_SECOND", "5:false:RANK_THIRD",
            "4:false:RANK_FOURTH", "3:false:RANK_FIFTH"}, delimiter = ':')
    void Decide_Rank(long targetCorrectCount, boolean isTargetBonused, LottoRank inputRank) {
        Assertions.assertThat(LottoRank.findLottoRank(targetCorrectCount, isTargetBonused)).isEqualTo(inputRank);
    }
}
