package lotto.model;

import lotto.exception.NotMultipleOfThousandException;
import lotto.exception.NotNumberException;
import lotto.exception.OverRangeException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class PaymentTest {
    @Test
    @DisplayName("Payment 생성자 예외처리")
    void Payment() {
        assertThatThrownBy(() -> {
            new Payment("a");
        }).isInstanceOf(NotNumberException.class)
        .hasMessage("숫자를 입력하세요.");

        assertThatThrownBy(() -> {
            new Payment("0");
        }).isInstanceOf(OverRangeException.class)
        .hasMessage("범위를 벗어났습니다.");

        assertThatThrownBy(() -> {
            new Payment("9999");
        }).isInstanceOf(NotMultipleOfThousandException.class)
        .hasMessage("천 단위로 입력하세요.");
    }
}
