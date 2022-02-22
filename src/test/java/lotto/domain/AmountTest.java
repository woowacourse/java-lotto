package lotto.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class AmountTest {
    @ParameterizedTest
    @ValueSource(strings = {"0", "1100", "-1"})
    @DisplayName("올바르지 않은 금액")
    void failed(String input) {
        assertThatThrownBy(() -> new Amount(input))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
