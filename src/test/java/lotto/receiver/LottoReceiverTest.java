package lotto.receiver;

import lotto.domain.LottoNumber;
import lotto.exception.LottoException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class LottoReceiverTest {

    @Test
    @DisplayName("입력된 당첨 번호를 검증하여 LottoNumber 리스트로 반환")
    void receive() {
        Assertions.assertThat(LottoReceiver.receive("45, 1, 10, 4, 20, 30"))
                .containsExactly(LottoNumber.NUMBER_1,
                        LottoNumber.NUMBER_4,
                        LottoNumber.NUMBER_10,
                        LottoNumber.NUMBER_20,
                        LottoNumber.NUMBER_30,
                        LottoNumber.NUMBER_45);
    }

    @ParameterizedTest(name = "숫자가 6개가 아닌 경우 예외 발생 - case : {0}")
    @ValueSource(strings = {"1, 2, 3, 4, 5", "1, 2, 3, 4, 5, 6, 7"})
    void checkSize(String input) {
        Assertions.assertThatThrownBy(() -> LottoReceiver.receive(input))
                .isInstanceOf(LottoException.class)
                .hasMessage("당첨 번호는 6개여야 합니다.");
    }

    @ParameterizedTest(name = "숫자가 중복되는 경우 예외 발생 - case : {0}")
    @ValueSource(strings = {"1, 1, 3, 4, 5, 6", "1, 2, 3, 4, 2, 6"})
    void checkNaturalNumber(String input) {
        Assertions.assertThatThrownBy(() -> LottoReceiver.receive(input))
                .isInstanceOf(LottoException.class)
                .hasMessage("당첨 번호는 중복될 수 없습니다.");
    }
}
