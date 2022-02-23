package lotto.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class WinningLottoTest {

    @Test
    @DisplayName("몇개나 맞았는지 테스트")
    void name() {
        PickedNumbers pickedNumbers = new PickedNumbers("1,2,3,4,5,6");
        BonusNumber bonusNumber = new BonusNumber("7", pickedNumbers);
        WinningLotto winningLotto = new WinningLotto(pickedNumbers, bonusNumber);
        Lotto lotto = new Lotto(new PickedNumbers("1,2,3,12,13,14"));
        Assertions.assertThat(winningLotto.findSameNumbersInPicked(lotto.getPickedNumbers())).isEqualTo(3);
    }
}
