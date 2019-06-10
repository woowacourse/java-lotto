package lotto.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LottoBuyingMoneyTest {
    @Test
    void lowerThanLottoPriceTest() {
        assertThrows(InvalidLottoBuyingMoneyException.class, () -> new LottoBuyingMoney(500));
    }

    @Test
    void modNotZeroTest() {
        assertThrows(InvalidLottoBuyingMoneyException.class, () -> new LottoBuyingMoney(1024));
    }

    @Test
    void getNumOfLottosTest() {
        assertThat((new LottoBuyingMoney(1000)).numOfLottos()).isEqualTo(1);
    }
}
