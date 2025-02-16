package model;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class PurchaseTest {
    @DisplayName("로또 구입 금액을 정상적으로 저장한다")
    @ParameterizedTest
    @CsvSource(value = {"1000:1000", "100000:100000", "15000:15000", "+1000:1000"}, delimiter = ':')
    void savePurchase(String inputString, int expectedOutput) {
        Purchase purchase = new Purchase(inputString);

        assertThat(purchase.getAmount()).isEqualTo(expectedOutput);
    }

    @DisplayName("로또 구입 금액 범위를 벗어날 경우 예외가 발생한다")
    @ParameterizedTest
    @ValueSource(strings = {"-1000", "0", "900", "101000"})
    void outOfPurchaseRange(String inputString) {
        assertThatThrownBy(() -> new Purchase(inputString))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("로또 구입 금액이 천원 단위가 아닐 경우 예외가 발생한다")
    @ParameterizedTest
    @ValueSource(strings = {"1500", "1050", "1001"})
    void outOfPurchaseUnit(String inputString) {
        assertThatThrownBy(() -> new Purchase(inputString))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("로또 구입 금액이 정수가 아닌 경우 예외가 발생한다")
    @ParameterizedTest
    @ValueSource(strings = {"1000.0", "1000원", "100o", "1000!"})
    void noIntegerValue(String inputString) {
        assertThatThrownBy(() -> new Purchase(inputString))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("구매 금액 천원 당 로또 1개를 발행한다")
    @ParameterizedTest
    @CsvSource(value = {"1000:1", "15000:15", "100000:100"}, delimiter = ':')
    void issueLotto(String inputString, int expectedCount) {
        Purchase purchase = new Purchase(inputString);

        assertThat(purchase.calculateLottoCount()).isEqualTo(expectedCount);
    }
}
