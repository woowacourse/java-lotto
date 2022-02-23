package lotto;

import static org.assertj.core.api.Assertions.*;
import java.math.BigDecimal;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MoneyTest {

    @Test
    @DisplayName("돈은 0이상이어야 한다.")
    void throwExceptionWhenNegative() {
        assertThatIllegalArgumentException()
            .isThrownBy(() -> new Money(-1L))
            .withMessageMatching("돈은 0이상이어야 한다.");
    }

    @Test
    @DisplayName("돈은 int의 범위를 넘어설 수 있다.")
    void checkOverInteger() {
        assertThat(new Money((long)Integer.MAX_VALUE + 1)).isNotNull();
    }

    @Test
    @DisplayName("돈의 합을 구할 수 있다.")
    void plusMoney() {
        Money money1 = new Money(2_000_000_000L);
        Money money2 = new Money(2_000_000_000L);

        assertThat(money1.plus(money2)).isEqualTo(new Money(4_000_000_000L));
    }

    @Test
    @DisplayName("돈을 나누면 비율을 반환한다.")
    void divideMoney() {
        Money totalMoney = new Money(50_000L);
        Money money = new Money(3_000L);

        assertThat(totalMoney.divide(money)).isEqualTo(new Rate(BigDecimal.valueOf(16.66)));
    }
}
