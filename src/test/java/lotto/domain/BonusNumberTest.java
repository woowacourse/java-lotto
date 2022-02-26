package lotto.domain;

import lotto.exception.BonusNumberException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class BonusNumberTest {

    @ParameterizedTest(name = "당첨 번호와 중복되는 보너스볼이 입력되면 예외발생 - case : {0}")
    @ValueSource(strings = {"1", "4"})
    void checkDuplication(String consoleInput) {
        WinningLotto winningLotto = WinningLotto.generateWinningLottoByConsole("1,4,10,20,30,45");
        Assertions.assertThatThrownBy(() -> BonusNumber.generateBonusNumberByConsole(consoleInput, winningLotto))
                .isInstanceOf(BonusNumberException.class)
                .hasMessage("보너스 번호는 당첨 번호와 중복될 수 없습니다.");
    }
}
