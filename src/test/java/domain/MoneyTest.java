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
        final Money money = new Money("1000");
        assertThat(money).isEqualTo(new Money("1000"));
    }

    @DisplayName("수익률이 정상적으로 계산되는지 확인한다.")
    @Test
    void name() {
        final Money money = new Money("10000");
        final Prize prize = Prize.getPrize(5000);

        final double profit = money.calculateProfit(prize);

        assertThat(profit).isEqualTo(0.5);
    }

    @DisplayName("로또 구입 금액 입력이 null 혹은 빈값인 경우 예외를 발생시킨다.")
    @NullAndEmptySource
    @ParameterizedTest
    void input_money_invalid_null(String inputMoney) {
        assertThatThrownBy(() -> new Money(inputMoney))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("null 또는 빈값을 입력할 수 없습니다.");
    }

    @DisplayName("로또 구입 금액 입력이 유효하지 않은 경우 예외를 발생시킨다.")
    @Test
    void input_money_invalid_negative_number() {
        assertThatThrownBy(() -> new Money("999"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("1000원 미만은 입력할 수 없습니다.");
    }

    @DisplayName("로또 구입 금액 입력이 음수인 경우 예외를 발생시킨다.")
    @ParameterizedTest
    @ValueSource(strings = {"0", "-1"})
    void input_money_invalid(String inputMoney) {
        assertThatThrownBy(() -> new Money(inputMoney))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("잘못된 값을 입력하였습니다.");
    }

    @DisplayName("구입금액에 맞는 로또 발급 갯수 반환을 확인한다.")
    @Test
    void calculateCounts() {
        final Money money = new Money("3500");

        final int actual = money.calculateCounts();

        assertThat(actual).isEqualTo(3);
    }

}
