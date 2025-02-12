package lotto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class CashierTest {

    @DisplayName("구입 금액 단위가 일치할 경우, 로또가 정상 발급된다.")
    @Test
    void ok() {
        Cashier cashier = new Cashier();
        List<Lotto> lottos = cashier.payForLotto(1_000);
        assertEquals(1, lottos.size());
    }

    @DisplayName("구입 금액 단위가 일치하지 않을 경우, 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(ints = {999, 1_001})
    void shouldThrowException_WhenInvalidUnit(int invalidAmount) {
        Cashier cashier = new Cashier();
        assertThrows(IllegalArgumentException.class, () -> cashier.payForLotto(invalidAmount));
    }
}
