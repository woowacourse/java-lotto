package lotto.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class LottoVendingMachineTest {
    @Test
    void validateLottoBuyingMoney() {
        assertThrows(InvalidLottoBuyingMoney.class, () -> LottoVendingMachine.getRandomLottos(1023));
    }
}
