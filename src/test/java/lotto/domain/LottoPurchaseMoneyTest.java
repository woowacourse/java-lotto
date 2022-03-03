package lotto.domain;

import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class LottoPurchaseMoneyTest {

    @Test
    @DisplayName("입력금액은 1,000원 미만이면 예외가 발생한다.")
    void throwExceptionWhenUnderThousands() {
        assertThatIllegalArgumentException()
            .isThrownBy(() -> new LottoPurchaseMoney(999))
            .withMessage("입력금액은 1,000원 이상이어야 한다.");
    }

    @ParameterizedTest
    @ValueSource(ints = {1_000, 100_000})
    @DisplayName("입력금액을 전달하면 InputMoney가 생성된다.")
    void createStore(int money) {
        assertThat(new LottoPurchaseMoney(money)).isNotNull();
    }

    @ParameterizedTest
    @CsvSource(value = {"1000,1", "2000, 1", "2000,2"})
    @DisplayName("1000원 단위로 로또를 구매할 수 있다.")
    void isBuyLotto(int totalMoney, int amount) {
        LottoPurchaseMoney lottoPurchaseMoney = new LottoPurchaseMoney(totalMoney);

        assertThat(lottoPurchaseMoney.isPurchaseLotto(amount)).isTrue();
    }

    @Test
    @DisplayName("구매가 불가능 한 개수일 경우 예외를 발생한다.")
    void isBuyLottos() {
        LottoPurchaseMoney lottoPurchaseMoney = new LottoPurchaseMoney(1000);

        assertThatIllegalArgumentException()
            .isThrownBy(() -> lottoPurchaseMoney.isPurchaseLotto(2))
            .withMessage("구매할 수 있는 수량이어야한다.");
    }

    @Test
    @DisplayName("현재 수량으로 몇장의 로또를 더 구매할 수 있는지 계산한다.")
    void calculateAvailableAmount() {
        LottoPurchaseMoney lottoPurchaseMoney = new LottoPurchaseMoney(5000);

        assertThat(lottoPurchaseMoney.calculateAvailablePurchaseAmount(3)).isEqualTo(2);
    }
}