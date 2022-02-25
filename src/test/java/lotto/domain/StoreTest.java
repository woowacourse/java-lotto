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
            .isThrownBy(() -> new Store(new Money(999)))
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
        Store store = new Store(new Money(1000));

        assertThat(store.buyLottos()).hasSize(1);
    }
}
