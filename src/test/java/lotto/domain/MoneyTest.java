package lotto.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MoneyTest {

    @Test
    @DisplayName("거스름돈을 return 하는 테스트")
    void changeMoney() {
        Money money = new Money("1100");

        Assertions.assertThat(money.changeMoney()).isEqualTo(100);
    }

    @Test
    @DisplayName("로또 티켓 겟수를 return 하는 테스트")
    void generateLottoTicketCount() {
        Money money = new Money("1100");

        Assertions.assertThat(money.generateLottoTicketCount()).isEqualTo(1);
    }
}