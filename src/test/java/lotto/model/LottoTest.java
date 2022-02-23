package lotto.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

class LottoTest {
    @Test
    @DisplayName("6개의 랜덤한 로또 번호를 생성하는지 확인")
    void shuffleTest() {
        Lotto lotto = Lotto.generate();

        assertThat(lotto.getSize()).isEqualTo(6);
    }

    @Test
    @DisplayName("당첨 번호를 비교한다")
    void matchNumber() {
        Lotto lotto = new Lotto(Arrays.asList(1, 2, 3, 4, 5, 6));
        WinningNumbers winningNumbers = new WinningNumbers(Arrays.asList(1, 2, 3, 4, 44, 45));

        int actual = lotto.match(winningNumbers);

        assertThat(actual).isEqualTo(4);
    }

    @Test
    @DisplayName("보너스 번호 당첨 여부를 확인한다")
    void matchBonusNumberTest() {
        Lotto lotto = new Lotto(Arrays.asList(1, 2, 3, 4, 5, 6));
        BonusNumber bonusNumber = new BonusNumber(6);

        boolean actual = lotto.matchBonusNumber(bonusNumber);

        assertThat(actual).isTrue();
    }

}
