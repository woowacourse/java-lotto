package lotto.domain;

import lotto.exception.LottoException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class LottoTest {

    @ParameterizedTest(name = "숫자가 6개가 아닌 경우 예외 발생 - case : {0}")
    @ValueSource(strings = {"1, 2, 3, 4, 5", "1, 2, 3, 4, 5, 6, 7"})
    void checkSize(String consoleInput) {
        Assertions.assertThatThrownBy(() -> Lotto.generateLottoByConsole(consoleInput))
                .isInstanceOf(LottoException.class)
                .hasMessage("당첨 번호는 6개여야 합니다.");
    }

    @ParameterizedTest(name = "숫자가 중복되는 경우 예외 발생 - case : {0}")
    @ValueSource(strings = {"1, 1, 3, 4, 5, 6", "1, 2, 3, 4, 2, 6"})
    void checkNaturalNumber(String consoleInput) {
        Assertions.assertThatThrownBy(() -> Lotto.generateLottoByConsole(consoleInput))
                .isInstanceOf(LottoException.class)
                .hasMessage("당첨 번호는 중복될 수 없습니다.");
    }

    @Test
    @DisplayName("당첨 번호와 보너스 번호를 이용해서 당첨 등급 검색")
    void getRank() {
        WinningLotto winningLotto = WinningLotto.generateWinningLottoByConsole("1,2,3,4,5,6");
        BonusNumber bonusNumber = BonusNumber.generateBonusNumberByConsole("7",winningLotto);
        Lotto lotto = Lotto.generateLottoByConsole("1,2,3,4,5,7");
        Assertions.assertThat(lotto.getRank(winningLotto, bonusNumber)).isEqualTo(Rank.RANK_2);
    }
}
