package lottogame.domain;

import lottogame.lottogameexception.InvalidLottoPriceException;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MoneyTest {
    @Test
    void 금액이_잘못되었으면_예외를_발생하는지_테스트() {
        assertThrows(InvalidLottoPriceException.class,()-> new Money(999));
    }

    @Test
    void 티켓_수량을_잘_리턴하는지_테스트() {
        Money money = new Money(10000);
        assertThat(money.getNumberOfTicket()).isEqualTo(10);
    }
}
