package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

class MoneyTest {

    @DisplayName("로또 구입 금액 입력이 유효한지 확인한다.")
    @Test
    void input_money_valid() {
        final Money money = new Money("14000");

        assertThat(money).isEqualTo(new Money("14000"));
    }

    @DisplayName("로또 구입 금액 입력이 유효하지 않은 경우 예외를 발생시킨다.")
    @NullAndEmptySource
    @ParameterizedTest
    @ValueSource(strings = {"-1", "0", "999"})
    void input_money_invalid(String inputMoney) {
        assertThatThrownBy(() -> new Money(inputMoney))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
