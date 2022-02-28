package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import java.math.BigDecimal;

import lotto.domain.vo.Money;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class MoneyTest {

    @Test
    @DisplayName("입력금액은 1,000원 미만이면 예외가 발생한다.")
    void throwExceptionWhenUnderThousands() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> Money.createLottoMoney(999))
                .withMessage("입력금액은 1,000 이상이어야 한다.");
    }

    @Test
    @DisplayName("상금은 0이상이어야 한다.")
    void throwExceptionWhenNegative() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> Money.createReward(-1L))
                .withMessageMatching("돈은 0이상이어야 한다.");
    }

    @Test
    @DisplayName("상금은 int의 범위를 넘어설 수 있다.")
    void checkOverInteger() {
        assertThat(Money.createReward((long) Integer.MAX_VALUE + 1)).isNotNull();
    }

    @Test
    @DisplayName("상금의 합을 구할 수 있다.")
    void plusReward() {
        Money money1 = Money.createReward(2_000_000_000L);
        Money money2 = Money.createReward(2_000_000_000L);

        assertThat(money1.plus(money2)).isEqualTo(Money.createReward(4_000_000_000L));
    }

    @Test
    @DisplayName("돈의 합을 구할 수 있다.")
    void plusMoney() {
        Money money1 = Money.createLottoMoney(1_000L);
        Money money2 = Money.createLottoMoney(2_000L);

        assertThat(money1.plus(money2)).isEqualTo(Money.createLottoMoney(3_000L));
    }

    @Test
    @DisplayName("돈의 차를 구할 수 있다.")
    void minusMoney() {
        Money money1 = Money.createLottoMoney(5_000L);
        Money money2 = Money.createLottoMoney(1_000L);

        assertThat(money1.minus(money2)).isEqualTo(Money.createLottoMoney(4_000L));
    }

    @Test
    @DisplayName("돈을 나누면 비율을 반환한다.")
    void divideMoney() {
        Money totalMoney = Money.createLottoMoney(50_000L);
        Money money = Money.createLottoMoney(3_000L);

        assertThat(totalMoney.divide(money)).isEqualTo(BigDecimal.valueOf(16.66));
    }

    @ParameterizedTest
    @CsvSource(value = {"1001,true", "1000,true"})
    @DisplayName("돈의 크기를 비교한다.")
    void throwExceptionWhenMinusMoneyIsZero(long money, boolean result) {
        Money money1 = Money.createLottoMoney(money);
        Money money2 = Money.createLottoMoney(1_000L);

        assertThat(money1.isGreaterThan(money2)).isEqualTo(result);
    }
}
