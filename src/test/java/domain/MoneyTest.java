package domain;

import exception.IllegalPositiveIntegerException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MoneyTest {
    @DisplayName("구입 금액이 양의 정수가 아니라면 예외가 발생한다")
    @Test
    void test() {
        Assertions.assertThatThrownBy(() -> new Money(-1000))
                .isInstanceOf(IllegalPositiveIntegerException.class);
    }

}