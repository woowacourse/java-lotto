import domain.Money;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class MoneyTest {

    @ParameterizedTest
    @ValueSource(ints = {1001, 1002, 1003, 1004, 1005, 1006, 1007, 1008, 1009})
    @DisplayName("10 단위가 아니면 예외 발생")
    void moneyExceptionNotDividable10(int amount) {
        assertThatThrownBy(() -> Money.from(amount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("금액은 10 단위");

    }

    @Test
    @DisplayName("돈 정상 생성")
    void moneyInsert() {
        assertDoesNotThrow(() -> Money.from(1500));
    }
}
