package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class MoneyTest {

    @DisplayName("로또 구입 금액 입력이 유효한지 확인한다.")
    @Test
    void input_money_valid() {
        final Money money = new Money(14000);

        assertThat(money).isEqualTo(new Money(14000));
    }

    @DisplayName("로또 구입 금액 입력이 유효하지 않은 경우 예외를 발생시킨다.")
    @ParameterizedTest
    @ValueSource(ints = {-1, 0, 999})
    void input_money_invalid(final int inputMoney) {
        assertThatThrownBy(() -> new Money(inputMoney))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("원 미만은 입력할 수 없습니다.");
    }

    @DisplayName("구입금액에 맞는 로또 발급 갯수 반환을 확인한다.")
    @Test
    void calculateCounts() {
        final Money money = new Money(3500);

        final int actual = money.calculateCounts();

        assertThat(actual).isEqualTo(3);
    }

    @DisplayName("구입금액에 맞는 로또 발급 갯수 반환을 확인한다.")
    @Test
    void calculateCounts() {
        final Money money = new Money("3500");

        final int actual = money.calculateCounts();

        assertThat(actual).isEqualTo(3);
    }
}
