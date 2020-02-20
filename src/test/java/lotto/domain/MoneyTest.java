package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import lotto.exceptions.InvalidMoneyException;

class MoneyTest {
    @ParameterizedTest(name = "{0}")
    @MethodSource("invalidMoneyParameters")
    @DisplayName("정상적이지 않은 Money 값 입력")
    void moneyUnder1000(String message, String amount) {
        assertThatThrownBy(() -> {
            new Money(amount);
        }).isInstanceOf(InvalidMoneyException.class);
    }

    static Stream<Arguments> invalidMoneyParameters() {
        return Stream.of(
            Arguments.of("입력이 숫자가 아닌 것을 포함할 때", "abc"),
            Arguments.of("입력 받은 구입 금액이 1000원 이하일 때", "0")
        );
    }

    @Test
    @DisplayName("입력 받은 금액으로 로또를 몇 개 살 수 있는지 테스트")
    void lottoTicketNumber() {
        assertThat(new Money("5000").ticketQuantity()).isEqualTo(5);
    }

    @ParameterizedTest
    @CsvSource({"5200,200", "5000,0"})
    void change(String amount, int expectedChange) {
        assertThat(new Money(amount).change()).isEqualTo(expectedChange);
    }
}