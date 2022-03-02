package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class StoreTest {

    private static final LottoMoney tenThousandMoney = LottoMoney.createLottoMoney(1_000);

    @ParameterizedTest
    @ValueSource(ints = {1_000, 100_000})
    @DisplayName("입력금액을 전달하면 Store가 생성된다.")
    void createStore(int money) {
        assertThat(new Store(LottoMoney.createLottoMoney(money))).isNotNull();
    }

    @Test
    @DisplayName("로또 한장을 생성한다.")
    void createLotto() {
        Store store = new Store(tenThousandMoney);

        assertThat(store.buyLottos(Collections.emptyList())).hasSize(1);
    }

    @Test
    @DisplayName("입력한 금액 보다 구매할 로또 개수의 가격이 더 큰경우 예외를 발생한다.")
    void throwExceptionWhenNotEnoughMoney() {
        Store store = new Store(tenThousandMoney);
        assertThatIllegalArgumentException()
                .isThrownBy(() -> store.buyLottos(List.of(
                        new Lotto(givenNumbers(1, 2, 3, 4, 5, 6)),
                        new Lotto(givenNumbers(1, 2, 3, 4, 5, 7)),
                        new Lotto(givenNumbers(1, 2, 3, 4, 5, 8))
                )))
                .withMessageMatching("로또를 구매할 돈이 부족하다.");
    }

    private static List<LottoNumber> givenNumbers(int... numbers) {
        return Arrays.stream(numbers)
                .mapToObj(LottoNumber::new)
                .collect(Collectors.toList());
    }
}
