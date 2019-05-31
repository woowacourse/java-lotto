package lotto.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class LottoVendingMachineTest {
    @Test
    void validateLottoBuyingMoneyTest() {
        assertThrows(InvalidLottoBuyingMoneyException.class, () ->
                new LottoVendingMachine(new Money(1023)));
    }

    @Test
    void validateNumOfCustomLottosTest() {
        assertThrows(InvalidNumOfCustomLottosException.class, () ->
                new LottoVendingMachine(new Money(1000)).validateNumOfCustomLottos(2));
    }
}
