package domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class MoneyTest {
    @DisplayName("Money 객체 생성이 올바르게 되는지 확인한다.")
    @Test
    void Money_생성_테스트() {
        // given
        int moneyValue = 1000;

        // when
        Money money = new Money(moneyValue);

        // then
        Assertions.assertThat(money).isInstanceOf(Money.class);
    }

    @DisplayName("로또 구매 가능 개수가 올바르게 반환되는지 확인한다.")
    @Test
    void 로또_구매_가능_개수_테스트() {
        // given
        int moneyValue = 1000;
        Money money = new Money(moneyValue);

        // when
        int actual = money.getBuyableLottoCount();

        // then
        int expected = 1;
        Assertions.assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("거스름돈 반환이 올바르게 되는지 확인한다.")
    @ParameterizedTest
    @CsvSource(value = {
            "1000, 0",
            "1005, 5",
            "1200, 200",
    })
    void 거스름돈_반환_테스트(int originMoney, int expectedChange) {
        // given
        Money money = new Money(originMoney);

        // when
        int actualChange = money.getChange();

        // then
        Assertions.assertThat(actualChange).isEqualTo(expectedChange);
    }
}