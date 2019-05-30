package lotto.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class LottoBuyerTest {
    @Test
    void 수동으로_입력할_로또의_갯수가_살수있는_로또의_수보다_많을경우() {
        LottoBuyer buyer = new LottoBuyer(new Budget(2000));
        assertThrows(NoMoneyException.class, () -> buyer.validateAffordability(3));
    }
}