import domain.Money;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class MoneyTest {

    @Test
    @DisplayName("10 단위가 아니면 예외 발생")
    void moneyExceptionNotDividable10() {
        assertThatThrownBy(() -> Money.from(1001))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("돈 정상 생성")
    void moneyInsert() {
        assertDoesNotThrow(() -> Money.from(1500));
    }
}
