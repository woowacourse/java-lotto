package lotto.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class UserMoneyTest {
    @Test
    void 금액_생성_실패() {
        assertThrows(MoneyException.class, () -> {
            new UserMoney(0);
        });
    }

    @Test
    void 로또_구매_실패() {
        assertThrows(MoneyException.class, () -> {
            new UserMoney(999).buy(1000);
        });
    }

    @Test
    void 거스름돈_확인() {
        UserMoney userMoney = new UserMoney(1300);
        userMoney.buy(1000);
        assertThat(userMoney.available()).isEqualTo(300);
    }

}