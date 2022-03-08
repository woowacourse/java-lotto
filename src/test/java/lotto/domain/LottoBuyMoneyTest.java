package lotto.domain;

import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class LottoBuyMoneyTest {

    @Test
    @DisplayName("입력금액은 1,000원 미만이면 예외가 발생한다.")
    void throwExceptionWhenUnderThousands() {
        assertThatIllegalArgumentException()
            .isThrownBy(() -> new LottoBuyMoney(999))
            .withMessage("입력금액은 1,000원 이상이어야 한다.");
    }

    @Test
    @DisplayName("입력금액은 1,000원 단위가 아니면 예외를 발생한다..")
    void throwExceptionWhenNotThousands() {
        assertThatIllegalArgumentException()
            .isThrownBy(() -> new LottoBuyMoney(1001))
            .withMessage("입력금액은 1,000원 단위어야 한다.");
    }


    @ParameterizedTest
    @ValueSource(ints = {1_000, 100_000})
    @DisplayName("입력금액을 전달하면 InputMoney가 생성된다.")
    void createStore(int money) {
        assertThat(new LottoBuyMoney(money)).isNotNull();
    }

    @ParameterizedTest
    @CsvSource(value = {"5,0", "4, 1", "3, 2"})
    @DisplayName("수동로또 개수로 자동로또를 몇개 구매할수있는지 구한다.")
    void calculateAvailableAmount(int manualAmount, int autoAmount) {
        LottoBuyMoney lottoBuyMoney = new LottoBuyMoney(5000);

        assertThat(lottoBuyMoney.countAutoAmountByManualAmount(manualAmount)).isEqualTo(autoAmount);
    }

    @Test
    @DisplayName("투입 금액보다 구매 개수가 많으면 예외를 발생한다.")
    void throwExceptionOverAmount() {
        LottoBuyMoney lottoBuyMoney = new LottoBuyMoney(5000);

        assertThatIllegalArgumentException()
            .isThrownBy(() -> lottoBuyMoney.countAutoAmountByManualAmount(6))
            .withMessage("구매 금액을 초과한 수량이다.");
    }

    @Test
    @DisplayName("구매 개수가 음수일 경우 예외를 발생한다.")
    void throwExceptionNegativeAmount() {
        LottoBuyMoney lottoBuyMoney = new LottoBuyMoney(5000);

        assertThatIllegalArgumentException()
            .isThrownBy(() -> lottoBuyMoney.countAutoAmountByManualAmount(-1))
            .withMessage("수량은 음수일 수 없다.");
    }
}