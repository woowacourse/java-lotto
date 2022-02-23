package lotto;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BonusNumberTest {

    @DisplayName("보너스 번호가 숫자가 아니면 예외가 발생한다")
    @Test
    void type_exception() {
        assertThatThrownBy(() -> {
            BonusNumber.from("일");
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 보너스 번호는 숫자로만 입력해주세요");
    }

    @DisplayName("보너스 번호가 1 이상 45 이하가 아니면 예외가 발생한다")
    @Test
    void bound_exception() {
        assertThatThrownBy(() -> {
            BonusNumber.from("0");
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 로또 번호는 1 이상 45 이하로 입력해주세요");
    }
}
