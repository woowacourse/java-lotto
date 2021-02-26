package lotto.domain;

import lotto.exception.IllegalManualLottoAmountException;
import lotto.exception.IllegalMoneyException;
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
        ManualLottoAmount manualLottoAmount = new ManualLottoAmount("1", new Money("1000"));
        assertThat(manualLottoAmount.getValue()).isEqualTo(1);
    }

    @DisplayName("숫자형식 오류 검증")
    @ParameterizedTest
    @ValueSource(strings = {"a", ";", "12df", " "})
    void validate(String input) {
        assertThatThrownBy(() -> new ManualLottoAmount(input, new Money("5000")))
                .isInstanceOf(IllegalManualLottoAmountException.class)
                .hasMessage(input + " : 올바른 형식이 아닙니다.");
    }

    @DisplayName("돈의 범위를 초과하는 오류 검증")
    @Test
    void validateByMoney() {
        assertThatThrownBy(() -> new ManualLottoAmount("5", new Money("4999")))
                .isInstanceOf(IllegalManualLottoAmountException.class)
                .hasMessage("5 : 소지금으로 구매할 수 있는 범위를 초과합니다.");
    }
}
