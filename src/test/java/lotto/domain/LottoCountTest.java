package lotto.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class LottoCountTest {
    @Test
    void countLowerThanZeroTest() {
        assertThrows(InvalidNumOfCustomLottosException.class, () -> new LottoCount(new LottoBuyingMoney(1000), -1));
    }

    @Test
    void countBiggerThanPossibleLottosTest() {
        assertThrows(InvalidNumOfCustomLottosException.class, () -> new LottoCount(new LottoBuyingMoney(1000), 2));
    }
}
