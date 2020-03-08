package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MoneyTest {
    @Test
    @DisplayName("돈이 양수일 때")
    void constructMoneyWithPositiveNumber() {
        //given
        int value = 1;
        //when & then
        assertThat(new Money(value)).isNotNull();
    }

    @Test
    @DisplayName("돈이 0보다 작거나 같을 때")
    void constructMoneyWithNumberWhichIsNotPositive() {
        //given
        int value = 0;
        //when & then
        assertThatThrownBy(() -> new Money(value))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("음수는 입력할 수 없습니다");
    }

    @Test
    @DisplayName("로또 갯수로 변환 1000원으로 나누어 떨어질 때")
    void toLottoSizeWithMoneyWhichCanBeDividedWith1000() {
        //given
        Money money = new Money(14_000);
        //when & then
        assertThat(money.toLottosSize()).isEqualTo(14);
    }

    @Test
    @DisplayName("로또 갯수로 변환 1000원으로 나누어 떨어지지 않을 때")
    void toLottoSizeWithMoneyWhichCanNotBeDividedWith1000() {
        //given
        Money money = new Money(14_100);
        //when & then
        assertThat(money.toLottosSize()).isEqualTo(14);
    }

    @Test
    @DisplayName("수익률로 변환")
    void toEarningRate() {
        //given
        Money purchaseAmount = new Money(14_000);
        Money totalWinningMoney = new Money(5_000);
        //when & then
        assertThat(totalWinningMoney.toEarningRate(purchaseAmount)).isEqualTo(35);
    }

    @Test
    @DisplayName("더하기")
    void add() {
        Money first = new Money(100);
        Money second = new Money(300);

        assertThat(first.add(second)).isEqualTo(new Money(400));
    }

    @Test
    @DisplayName("곱하기")
    void multiply() {
        Money first = new Money(100);
        int size = 3;

        assertThat(first.multiply(size)).isEqualTo(new Money(300));
    }
}
