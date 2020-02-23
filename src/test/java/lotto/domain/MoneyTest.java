package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

class MoneyTest {
    @Test
    void 돈이_양수일_때() {
        //given
        int value = 1;
        //when & then
        assertThat(new Money(value)).isNotNull();
    }

    @Test
    void 돈이_양수이고_타입이_double일_때() {
        //given
        double value = 1;
        //when & then
        assertThat(new Money(value)).isNotNull();
    }

    @Test
    void 돈이_0보다_작거나_같을_때() {
        //given
        int value = 0;
        //when & then
        assertThatThrownBy(() -> new Money(value))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("음수는 입력할 수 없습니다");
    }

    @Test
    void 로또_갯수로_변환_1000원으로_나누어_떨어질_때() {
        //given
        Money money = new Money(14_000);
        //when & then
        assertThat(money.toLottosSize()).isEqualTo(14);
    }

    @Test
    void 로또_갯수로_변환_1000원으로_나누어_떨어지지_않을_때() {
        //given
        Money money = new Money(14_100);
        //when & then
        assertThat(money.toLottosSize()).isEqualTo(14);
    }

    @Test
    void 수익률로_변환() {
        //given
        Money purchaseAmount = new Money(14_000);
        Money totalWinningMoney = new Money(5_000);
        //when & then
        assertThat(totalWinningMoney.toEarningRate(purchaseAmount)).isEqualTo(35);
    }
}
