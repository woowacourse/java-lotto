package lotto.domain.vo;

import static org.assertj.core.api.Assertions.*;
import java.math.BigDecimal;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

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
    @DisplayName("돈의 차를 구할 수 있다.")
    void minusMoney() {
        Money money1 = new Money(5_000L);
        Money money2 = new Money(1_000L);

        assertThat(money1.minus(money2)).isEqualTo(new Money(4_000L));
    }

    @Test
    @DisplayName("돈을 나누면 비율을 반환한다.")
    void divideMoney() {
        Money totalMoney = new Money(50_000L);
        Money money = new Money(3_000L);

        assertThat(totalMoney.divide(money)).isEqualTo(BigDecimal.valueOf(16.66));
    }

    @ParameterizedTest
    @CsvSource(value = {"1001,true", "1000,false", "999,false"})
    @DisplayName("돈의 크기를 비교한다.")
    void throwExceptionWhenMinusMoneyIsZero(long money, boolean result) {
        Money money1 = new Money(money);
        Money money2 = new Money(1_000L);

        assertThat(money1.isGreaterThan(money2)).isEqualTo(result);
    }
}
