package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import lotto.domain.vo.Money;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class StoreTest {


    @ParameterizedTest
    @ValueSource(ints = {1_000, 100_000})
    @DisplayName("입력금액을 전달하면 Store가 생성된다.")
    void createStore(int money) {
        assertThat(new Store(Money.createMoney(money))).isNotNull();
    }

    @Test
    @DisplayName("로또 한장을 생성한다.")
    void createLotto() {
        Store store = new Store(Money.createMoney(1000));

        assertThat(store.buyLottos()).hasSize(1);
    }

}
