package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class MoneyTest {

    @DisplayName("Money를 생성하는 기능")
    @Test
    void generate() {
        //given
        int moneyValue = 1_000;

        //when
        Money money = Money.createPurchasingLottoMoney(moneyValue);

        //then
        assertThat(money).isNotNull();
    }

    @DisplayName("Money 값으로 음수가 입력되는 경우")
    @ParameterizedTest
    @ValueSource(ints = {
            -1, -2, -3, -4, -100, -1_000
    })
    void generateMoneyWithNegativeValue(int moneyValue) {
        //when //then
        assertThatThrownBy(() -> Money.createPurchasingLottoMoney(moneyValue))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("Money 값으로 로또 가격의 정수배가 아닌 값이 입력되는 경우")
    @ParameterizedTest
    @ValueSource(ints = {
            1, 1_001, 1111, 12345
    })
    void generateMoneyWithNotMultipleOfLottoPrice(int moneyValue) {
        //when //then
        assertThatThrownBy(() -> Money.createPurchasingLottoMoney(moneyValue))
                .isInstanceOf(IllegalArgumentException.class);
    }


    @DisplayName("구입금액을 특정 값으로 나눈 값을 반환하는 기능")
    @Test
    void divide() {
        //given
        Money money = Money.createPurchasingLottoMoney(5000L);

        //when
        long number = money.divide(1000L);

        //then
        assertThat(number).isEqualTo(5);
    }

    @DisplayName("구입금액을 0이나 음수로 나눈 값을 반환하는 기능")
    @ParameterizedTest
    @ValueSource(longs = {
            0, -1, -2, -3
    })
    void divide(long value) {
        //given
        Money money = Money.createPurchasingLottoMoney(1000L);

        //when //then
        assertThatThrownBy(() -> money.divide(value))
                .isExactlyInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("돈을 더하는 기능을 테스트한다")
    @Test
    void add() {
        //given
        Money firstMoney = Money.createPurchasingLottoMoney(1_000L);
        Money secondMoney = Money.createPurchasingLottoMoney(2_000L);

        //when
        Money sumMoney = firstMoney.add(secondMoney);

        //then
        assertThat(sumMoney).isEqualTo(Money.valueOf(3_000L));
    }

    @DisplayName("돈을 곱하는 기능")
    @Test
    void multiply() {
        //given
        Money money = Money.createPurchasingLottoMoney(1_000L);
        long multipliedNumber = 3L;

        //when
        Money result = money.multiply(multipliedNumber);

        //then
        assertThat(result).isEqualTo(Money.valueOf(3_000L));
    }

    @DisplayName("수익율을 계산하는 기능")
    @Test
    void calculateEarningRate() {
        //given
        Money usedMoney = Money.createPurchasingLottoMoney(14_000L);
        Money winningMoney = Money.valueOf(5_000L);

        //when
        double earningRate = winningMoney.calculateEarningRate(usedMoney);

        //then
        assertThat(earningRate).isEqualTo(0.35D);
    }
}
