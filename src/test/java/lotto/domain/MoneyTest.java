package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import lotto.exceptions.InvalidManualQuantityException;
import lotto.exceptions.InvalidMoneyException;

class MoneyTest {

    public static final String AMOUNT = "5000";
    public static final String EXCEED_MONEY_QUANTITY = "6";

    @ParameterizedTest(name = "{1}")
    @MethodSource("invalidMoneyParameters")
    @DisplayName("정상적이지 않은 Money 값 입력 시 예외를 발생시키는지")
    void moneyUnder1000(String amount, String message) {
        assertThatThrownBy(() -> {
            new Money(amount);
        }).isInstanceOf(InvalidMoneyException.class);
    }

    static Stream<Arguments> invalidMoneyParameters() {
        return Stream.of(
            Arguments.of("abc", "입력이 숫자가 아닌 것을 포함할 때"),
            Arguments.of("0", "입력 받은 구입 금액이 1000원 이하일 때")
        );
    }

    @Test
    @DisplayName("입력 받은 금액으로 로또를 몇 개 살 수 있는지")
    void lottoTicketNumber() {
        assertThat(Money.create("5000").calculateTicketQuantity()).isEqualTo(5);
    }

    @ParameterizedTest
    @DisplayName("거스름돈이 제대로 반환되는지")
    @CsvSource({"5200,200", "5000,0"})
    void change(String amount, int expectedChange) {
        assertThat(Money.create(amount).change()).isEqualTo(expectedChange);
    }



    @ParameterizedTest(name = "{1}")
    @MethodSource("invalidManualQuantityParameters")
    @DisplayName("정상적이지 않은 수동 로또 갯수 입력 시 예외를 발생시키는지")
    void InvalidManualQuantity(String amount, String message) {
        Money money = new Money(AMOUNT);
        assertThatThrownBy(() -> {
            money.getValidatedManualQuantity(amount);
        }).isInstanceOf(InvalidManualQuantityException.class);
    }

    static Stream<Arguments> invalidManualQuantityParameters() {
        return Stream.of(
            Arguments.of("abc", "입력이 숫자가 아닌 것을 포함할 때"),
            Arguments.of("-1", "입력 받은 수동 로또 개수가 음수일 때"),
            Arguments.of(EXCEED_MONEY_QUANTITY, "입력받은 수동 로또 개수가 금액을 초과할 때")
        );
    }


}