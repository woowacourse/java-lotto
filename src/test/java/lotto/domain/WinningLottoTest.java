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
    @CsvSource(value = {"1,2,3,4,5,6:RANK_FIRST", "1,2,3,4,5,7:RANK_SECOND", "1,2,3,4,5,8:RANK_THIRD",
            "1,2,3,4,9,7:RANK_FOURTH", "1,2,3,11,12,13:RANK_FIFTH"}, delimiter = ':')
    void Decide_Rank(String input, LottoRank inputRank) {
        PickedNumbers pickedNumbers = new PickedNumbers("1,2,3,4,5,6");
        BonusNumber bonusNumber = new BonusNumber("7", pickedNumbers);
        WinningLotto winningLotto = new WinningLotto(pickedNumbers, bonusNumber);

        Assertions.assertThat(winningLotto.findLottoRank(new PickedNumbers(input))).isEqualTo(inputRank);
    }
}
