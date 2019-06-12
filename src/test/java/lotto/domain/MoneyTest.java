package lotto.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MoneyTest {
    @Test
    void 천원_단위가_아닌경우() {
        assertThrows(IllegalArgumentException.class, () -> Money.from(1300));
    }

    @Test
    void 구매_개수_확인() {
        assertThat(10).isEqualTo(Money.from(10000).getCountOfPurchase());
    }

    @Test
    void 천원_이하_입력() {
        assertThrows(IllegalArgumentException.class, () -> Money.from(0));
    }

    @Test
    void getCountOfAutoTest() {
        Money money = Money.from(100000);
        int countOfManual = 5;
        int actual = (int) money.value() / Money.PRICE_PER_LOTTO - countOfManual;

        assertThat(actual).isEqualTo(money.getCountOfAuto(countOfManual));
    }
}
