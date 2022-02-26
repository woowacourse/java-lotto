package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class MoneyTest {

    @Test
    @DisplayName("금액에 음수를 입력했을 경우")
    void input_negative() {
        assertThatThrownBy(() -> {
            new Money(-1000);
        }).isInstanceOf(IllegalArgumentException.class);
    }
}
