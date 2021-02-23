package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import lotto.domain.Money;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MoneyTest {

    @Test
    @DisplayName("구입 금액을 숫자로 관리한다.")
    public void 구입_금액을_숫자로_관리한다() {
        Money money = new Money("1000");
        assertThat(money.getValue()).isEqualTo(1000);
    }

    @Test
    @DisplayName("구입 금액은 숫자를 입력 받아야 한다.")
    public void 구입_금액은_숫자를_입력_받아야_한다() {
        assertThatExceptionOfType(IllegalArgumentException.class)
            .isThrownBy(() -> {
                Money money = new Money("Money");
            });
    }

    @Test
    @DisplayName("구입 금액은 음수 여서는 안된다")
    public void 구입_금액은_음수_여서는_안된다() {
        assertThatExceptionOfType(IllegalArgumentException.class)
            .isThrownBy(() -> {
                Money money = new Money("-1");
            });
    }

}
