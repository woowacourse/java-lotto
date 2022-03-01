package lotto.domain;

import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

public class StoreTest {

    @Test
    @DisplayName("입력금액은 1,000원 미만이면 예외가 발생한다.")
    void throwExceptionWhenUnderThousands() {
        assertThatIllegalArgumentException()
            .isThrownBy(() -> new Store(999))
            .withMessage("입력금액은 1,000원 이상이어야 한다.");
    }

    @ParameterizedTest
    @ValueSource(ints = {1_000, 100_000})
    @DisplayName("입력금액을 전달하면 Store가 생성된다.")
    void createStore(int money) {
        assertThat(new Store(money)).isNotNull();
    }

    @Test
    @DisplayName("1000원으로 로또 한장을 구매할 수 있다.")
    void createLotto() {
        Store store = new Store(1000);

        assertThat(store.buyAutoLottos()).hasSize(1);
    }

    @Test
    @DisplayName("수동로또 구매 후 남은 금액만큼 자동 로또를 생성한다.")
    void buyManualLottto() {
        Store store = new Store(3000);
        store.buyManualLottos(2);

        assertThat(store.buyAutoLottos()).hasSize(1);
    }

    @Test
    @DisplayName("입력금액이 1000원 단위가 아니면 예외를 발생한다.")
    void throwExceptionWhenHasRemainder() {
        assertThatIllegalArgumentException()
            .isThrownBy(() -> new Store(1100))
            .withMessage("입력금액은 1,000원 단위어야 한다.");
    }

    @ParameterizedTest
    @CsvSource(value = {"1, true", "0, false"})
    @DisplayName("수동 로또는 1개 이상만 구매가 가능하다.")
    void isBuyManualLotto(int amount, boolean expected) {
        Store store = new Store(1000);

        assertThat(store.isBuyManualLotto(amount)).isEqualTo(expected);
    }
}
