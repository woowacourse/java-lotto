package lotto.domain.manager;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MoneyTest {

    @Test
    @DisplayName("양수인지 확인")
    void validatePositiveInt() {
        assertThatThrownBy(() -> new Money(0))
            .isInstanceOf(IllegalArgumentException.class).hasMessageContaining("양의 정수");
    }

}
