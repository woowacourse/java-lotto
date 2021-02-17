package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MoneyTest {

    @DisplayName("생성하기")
    @Test
    void create() {
        Money money = new Money(1000);
        assertThat(money).isEqualTo(new Money(1000));
    }

    @DisplayName("입력 금액은 1000원 이상이어야 한다.")
    @Test
    void checkMoney() {
        assertThatIllegalArgumentException()
            .isThrownBy(() -> new Money(0));

        assertThatIllegalArgumentException()
            .isThrownBy(() -> new Money(999));
    }
}
