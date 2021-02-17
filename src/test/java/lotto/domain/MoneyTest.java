package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import lotto.domain.Money;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MoneyTest {

    @Test
    @DisplayName("음수가 아닌지 확인")
    void validateNonNegativeInt() {
        assertThatThrownBy(() -> new Money(-1))
            .isInstanceOf(IllegalArgumentException.class).hasMessageContaining("음의 정수");
    }

}
