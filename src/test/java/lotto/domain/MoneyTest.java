package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MoneyTest {

    @DisplayName("생성하기")
    @Test
    void create() {
        Money money = new Money(1000);
        assertThat(money).isEqualTo(new Money(1000));
    }

    @DisplayName("입력 금액은 1000원 이상이어야 한다.")
    @Test
    void checkMoney() {
        assertThatIllegalArgumentException()
            .isThrownBy(() -> new Money(0));

        assertThatIllegalArgumentException()
            .isThrownBy(() -> new Money(999));
    }

    @DisplayName("입력금액을 1000으로 나눈 몫만큼 티켓을 만들 수 있다.")
    @Test
    void countTickets() {
        assertThat(new Money(3000).countTickets()).isEqualTo(3);
        assertThat(new Money(4500).countTickets()).isEqualTo(4);
    }

    @DisplayName("입력금액이 1000원 단위로 나누어 떨어지지 않으면 거스름돈이 생긴다.")
    @Test
    void charge(){
        assertThat(new Money(3000).calculateCharge()).isEqualTo(0);
        assertThat(new Money(3800).calculateCharge()).isEqualTo(800);
    }

    @DisplayName("구입하려는 수동 티켓 수가 입력금액으로 살 수 있는 티켓 수보다 많을 경우 에러를 발생시킨다.")
    @Test
    void limit(){
        Money money = new Money(5000);
        assertThatIllegalArgumentException()
            .isThrownBy(() -> money.checkLimit(10));
    }

    @DisplayName("구입 금액과 리워드를 이용해 수익률을 계산할 수 있다.")
    @Test
    void calculateProfit() {
        assertThat(new Money(5000).calculateProfit(5000)).isEqualTo(1.00f);
    }
}
