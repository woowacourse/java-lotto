package domain;

import static domain.Money.ERROR_LOWER_THAN_LOTTO_PRICE_MESSAGE;
import static domain.Money.LOTTO_PRICE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static utils.Validator.ERROR_FORMAT_MESSAGE;
import static utils.Validator.ERROR_NULL_OR_EMPTY_MESSAGE;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

class MoneyTest {

    @DisplayName("로또 구입 금액 입력이 유효한지 확인한다.")
    @Test
    void input_money_valid() {
        final Money money = new Money("1000");
        assertThat(money).isEqualTo(new Money("1000"));
    }

    @DisplayName("로또 구입 금액 입력이 null 혹은 빈값인 경우 예외를 발생시킨다.")
    @NullAndEmptySource
    @ParameterizedTest
    void input_money_invalid_null(String inputMoney) {
        assertThatThrownBy(() -> new Money(inputMoney))
                .isInstanceOf(IllegalArgumentException.class).hasMessageContaining(ERROR_NULL_OR_EMPTY_MESSAGE);
    }

    @DisplayName("로또 구입 금액 입력이 유효하지 않은 경우 예외를 발생시킨다.")
    @Test
    void input_money_invalid_negative_number() {
        assertThatThrownBy(() -> new Money("999"))
                .isInstanceOf(IllegalArgumentException.class).hasMessageContaining(LOTTO_PRICE + ERROR_LOWER_THAN_LOTTO_PRICE_MESSAGE);
    }

    @DisplayName("로또 구입 금액 입력이 음수인 경우 예외를 발생시킨다.")
    @ParameterizedTest
    @ValueSource(strings = {"0", "-1"})
    void input_money_invalid(String inputMoney) {
        assertThatThrownBy(() -> new Money(inputMoney))
                .isInstanceOf(IllegalArgumentException.class).hasMessageContaining(ERROR_FORMAT_MESSAGE);
    }

    @DisplayName("구입금액에 맞는 로또 발급 갯수 반환을 확인한다.")
    @Test
    void calculateCounts() {
        final Money money = new Money("3500");

        final int actual = money.calculateCounts();

        assertThat(actual).isEqualTo(3);
    }
}
