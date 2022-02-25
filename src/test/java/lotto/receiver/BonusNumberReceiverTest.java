package lotto.receiver;

import lotto.domain.LottoNumber;
import lotto.domain.WinningLotto;
import lotto.exception.BonusNumberException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class BonusNumberReceiverTest {

    @Test
    @DisplayName("입력된 보너스 번호를 검증하여 LottoNumber 로 반환")
    void receive() {
        WinningLotto winningLotto = new WinningLotto("1,2,3,4,5,6");
        Assertions.assertThat(BonusNumberReceiver.receive("12", winningLotto))
                .isEqualTo(LottoNumber.NUMBER_12);
    }

    @ParameterizedTest(name = "당첨 번호와 중복되는 보너스볼이 입력되면 예외발생 - case : {0}")
    @ValueSource(strings = {"1", "4"})
    void checkDuplication(String input) {
        WinningLotto winningLotto = new WinningLotto("1,4,10,20,30,45");
        Assertions.assertThatThrownBy(() -> BonusNumberReceiver.receive(input, winningLotto))
                .isInstanceOf(BonusNumberException.class)
                .hasMessage("보너스 번호는 당첨 번호와 중복될 수 없습니다.");
    }
}
