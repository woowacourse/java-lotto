package domain;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MoneyTest {

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

    @Test
    @DisplayName("돈을 차감할 때 돈이 모자라면 예외")
    void moneyConsume() {
        Money money = Money.from(1000);
        Money other = Money.from(2010);

        assertThatThrownBy(() -> money.consume(other))
            .isInstanceOf(IllegalArgumentException.class);
    }
}
