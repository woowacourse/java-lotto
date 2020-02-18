package lotto.view;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class PayTest {
    @ParameterizedTest
    @CsvSource(value = {"999, false", "1001, true"})
    @DisplayName("입력한 금액이 1000원 미만일 경우")
    void isUnderK(int 입력, boolean 결과) {
        assertThat(InputView.isUnderK(입력)).isEqualTo(결과);
    }

    @Test
    @DisplayName("입력한 금액이 숫자가 아닌 경우")
    void checkNumberFormat() {
        assertThatThrownBy(() -> {
            InputView.checkNumberFormat("a");
        });
    }

    @ParameterizedTest
    @CsvSource(value = {"999, false", "99999, true", "100001, false"})
    @DisplayName("금액범위를 벗어난 경우 (1000 부터 10만 까지)")
    void isValueRange(int input, boolean expected) {
        assertThat(InputView.isValueRange(input)).isEqualTo(expected);
    }
}
