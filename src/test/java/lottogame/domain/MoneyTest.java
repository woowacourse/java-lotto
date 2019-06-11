package lottogame.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class MoneyTest {
    @Test
    void 티켓_수량을_잘_리턴하는지_테스트() {
        Money money = Money.generate("10000");
        assertThat(money.getNumberOfTicket()).isEqualTo(10);
    }

    @Test
    void 돈이_제대로_생성_안_됐을_때_겁사() {
        Money money = Money.generate("999");
        assertThat(money.isNotCreatedWell()).isTrue();
    }
}
