package lotto.domain;

import static org.assertj.core.api.Assertions.*;
import lotto.domain.vo.Money;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class StoreTest {

    @Test
    @DisplayName("입력금액은 1,000원 미만이면 예외가 발생한다.")
    void throwExceptionWhenUnderThousands() {
        assertThatIllegalArgumentException()
            .isThrownBy(() -> new Store(new Money(999L)))
            .withMessage("입력금액은 1,000원 이상이어야 한다.");
    }

    @ParameterizedTest
    @ValueSource(ints = {1_000, 100_000})
    @DisplayName("입력금액을 전달하면 Store가 생성된다.")
    void createStore(int money) {
        assertThat(new Store(new Money(money))).isNotNull();
    }

    @Test
    @DisplayName("1000원으로 로또 한장을 구매할 수 있다.")
    void createLotto() {
        Store store = new Store(new Money(1000L));

        assertThat(store.buyLottos()).hasSize(1);
    }

    @Test
    @DisplayName("입력금액이 1000원 단위가 아니면 예외를 발생한다.")
    void throwExceptionWhenHasRemainder() {
        assertThatIllegalArgumentException()
            .isThrownBy(() -> new Store(new Money(1100L)))
            .withMessage("입력금액은 1,000원 단위어야 한다.");
    }
}
