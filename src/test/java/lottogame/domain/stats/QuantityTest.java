package lottogame.domain.stats;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class QuantityTest {
    @Test
    @DisplayName("같은 값을 가지면 같은 객체인지 확인")
    void of1() {
        Quantity quantity = Quantity.of("10");
        assertEquals(Quantity.of("10"), quantity);
    }

    @ParameterizedTest
    @DisplayName("0이상의 정수가 아닌 다른 값을 넣으면 예외 발생하는지 확인")
    @ValueSource(strings = {"1abc", "5가", "-1", "-5", "-10"})
    void of2(String input) {
        assertThatThrownBy(() -> Quantity.of(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("잘못된 로또 수입니다.");
    }

    @ParameterizedTest
    @DisplayName("0이상의 정수를 넣으면 값이 잘 들어가는지 확인")
    @CsvSource(value = {"0:0", "1:1", "5:5", "10:10", "500:500"}, delimiter = ':')
    void of3(String actualValue, int expectedValue) {
        Quantity quantity = Quantity.of(actualValue);
        assertEquals(expectedValue, quantity.value());
    }

    @ParameterizedTest
    @DisplayName("올바른 금액과 부분 갯수를 넣으면 나머지 갯수가 나오는지 확인")
    @CsvSource(value = {"1000:0:1", "1000:1:0", "5000:0:5", "5000:2:3", "5000:3:2", "5000:5:0"}, delimiter = ':')
    void from1(String moneyInput, String quantityInput, int expectedValue) {
        Money money = Money.of(moneyInput);
        Quantity manualQuantity = Quantity.of(quantityInput);
        Quantity autoQuantity = Quantity.from(money, manualQuantity);
        assertEquals(expectedValue, autoQuantity.value());
    }

    @Test
    @DisplayName("금액에서 나오는 총 갯수보다 부분 갯수가 크면 예외가 발생하는지 확인")
    void from2() {
        Money money = Money.of("1000");
        Quantity manualQuantity = Quantity.of("2");
        assertThatThrownBy(() -> Quantity.from(money, manualQuantity))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("잘못된 로또 수입니다.");
    }

    @Test
    @DisplayName("갯수가 0인지 확인")
    void isZero() {
        Quantity zeroQuantity = Quantity.of("0");
        assertTrue(zeroQuantity.isZero());
        Money money = Money.of("5000");
        Quantity manualQuantity = Quantity.of("5");
        Quantity autoQuantity = Quantity.from(money, manualQuantity);
        assertTrue(autoQuantity.isZero());
    }
}
