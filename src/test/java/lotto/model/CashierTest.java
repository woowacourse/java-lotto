package lotto.model;

import static org.assertj.core.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class CashierTest {

    @DisplayName("구입 금액 단위가 일치할 경우, 로또가 정상 발급된다.")
    @ParameterizedTest
    @CsvSource(value = {
            "1_000, 1",
            "10_000, 10"
    }, delimiter = ',')
    void ok(int amount, int count) {
        Cashier cashier = new Cashier();
        List<Lotto> lottos = cashier.payForLotto(amount);

        assertThat(lottos.size()).isEqualTo(count);
    }

    @DisplayName("구입 금액 단위가 일치하지 않을 경우, 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(ints = {999, 1_001})
    void shouldThrowException_WhenInvalidUnit(int invalidAmount) {
        Cashier cashier = new Cashier();

        assertThatThrownBy(() -> cashier.payForLotto(invalidAmount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("로또는 1000원 단위로 구매할 수 있습니다.");
    }
}
