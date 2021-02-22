package lotto;


import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import org.junit.jupiter.api.Test;

public class MoneyTest {

    @Test
    void 정상적인_가격() {
        Money money = new Money("14000");
        assertThat(money.getPrice()).isEqualTo(14000);
    }

    @Test
    void 띄어쓰기_불가능() {
        assertThatIllegalArgumentException().isThrownBy(() -> {
            Money money = new Money("140 00");
        });
    }

    @Test
    void 음수_불가능() {
        assertThatIllegalArgumentException().isThrownBy(() -> {
            Money money = new Money("-14000");
        });
    }
}
