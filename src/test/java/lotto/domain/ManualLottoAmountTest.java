package lotto.domain;

import lotto.exception.IllegalManualLottoAmountException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ManualLottoAmountTest {

    @DisplayName("수동로또 개수 생성 테스트")
    @Test
    void create() {
        ManualLottoAmount manualLottoAmount = new ManualLottoAmount("5", 7);
        assertThat(manualLottoAmount.getValue()).isEqualTo(5);
    }

    @DisplayName("숫자형식 오류 검증")
    @ParameterizedTest
    @ValueSource(strings = {"a", ";", "12df", " "})
    void validate(String input) {
        assertThatThrownBy(() -> new ManualLottoAmount(input, 7))
                .isInstanceOf(IllegalManualLottoAmountException.class)
                .hasMessage(input + " : 올바른 형식이 아닙니다.");
    }
}
