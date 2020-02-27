package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class WinningLottoTest {

    @DisplayName("당첨 번호와 중복되는 보너스 볼 입력 시 예외가 발생하는지 확인")
    @ParameterizedTest
    @ValueSource(strings = {"1", "2", "3", "4", "5", "6"})
    void validateDuplicatedNumberTest(int input) {
        String[] numbers = {"1", "2", "3", "4", "5", "6"};
        Lotto winningLottoLine = Lotto.from(numbers);
        LottoNumber bonusNumber = LottoNumber.of(input);

        assertThatThrownBy(() -> new WinningLotto(winningLottoLine, bonusNumber))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("당첨 번호와 중복되는 보너스 볼입니다.");
    }
}
