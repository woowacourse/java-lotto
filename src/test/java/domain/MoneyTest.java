package domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

public class MoneyTest {
    @ParameterizedTest
    @ValueSource(ints = {1000, 2000, 3000})
    @DisplayName("Money생성 확인")
    void test1(int input) {
        Assertions.assertThatCode(() -> new Money(input)).doesNotThrowAnyException();
    }

    @ParameterizedTest
    @ValueSource(ints = {0, -1, -1000})
    @DisplayName("양수값이 아니면 예외처리")
    void test2(int input) {
        Assertions.assertThatThrownBy(() -> new Money(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("금액은 0 이하로 입력될 수 없습니다.");
    }

    @ParameterizedTest
    @ValueSource(ints = {50, 1570, 999})
    @DisplayName("잔돈이 남는 경우 예외처리")
    void test3(int input) {
        Assertions.assertThatThrownBy(() -> new Money(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("잔돈이 남습니다. 결제할 수 없습니다.");
    }

    @ParameterizedTest
    @CsvSource(value = {"100000,10000", "200000,20000", "1000,100", "700,70"})
    @DisplayName("수익률 계산 확인")
    void test4(int input, int result) {
        Money money = new Money(1000);
        Assertions.assertThat(money.calculateEarningRate(input)).isEqualTo(result);
    }

    @ParameterizedTest
    @CsvSource(value = {"100000,100,50", "200000,200,100", "1000,1,0"})
    @DisplayName("금액에 대한 게임수 계산 확인")
    void test5(int value, int totalRepeat) {
        Money money = new Money(value);
        Assertions.assertThat(money.createRepeatCount())
                .hasFieldOrPropertyWithValue("repeatCount", totalRepeat);
    }
}
