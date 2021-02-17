import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MoneyTest {

    @Test
    @DisplayName("Money에 문자를 넣었을때")
    void moneyValueIsString() {
        assertThatThrownBy(() -> new Money("abc"))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("Money에 음수를 넣었을때")
    void moneyValueIsNegativeNumber() {
        assertThatThrownBy(() -> new Money("-1"))
            .isInstanceOf(IllegalArgumentException.class);
    }
}
