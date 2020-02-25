package domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

public class MoneyTest {
    @ParameterizedTest
    @ValueSource(ints = {1000, 2000, 3000})
    void Money생성_확인(int input) {
        Money money = new Money(input);
        assertThat(money).hasFieldOrPropertyWithValue("money", input);
    }

    @ParameterizedTest
    @ValueSource(ints = {0, -1, -1000})
    void 양수값이_아니면_예외처리(int input) {
        Assertions.assertThatThrownBy(() -> new Money(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("금액은 0 이하로 입력될 수 없습니다.");
    }

    @ParameterizedTest
    @ValueSource(ints = {50, 1570, 999})
    void 잔돈이_남는_경우_예외처리(int input) {
        Assertions.assertThatThrownBy(() -> new Money(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("잔돈이 남습니다. 결제할 수 없습니다.");
    }

    @ParameterizedTest
    @CsvSource(value = {"100000,10000", "200000,20000", "1000,100", "700,70"})
    void 수익률_계산_확인(int input, int result) {
        Money money = new Money(1000);
        Assertions.assertThat(money.calculateEarningRate(input)).isEqualTo(result);
    }

    @ParameterizedTest
    @CsvSource(value = {"100000,100,50", "200000,200,100", "1000,1,0"})
    void 금액에_대한_게임수_계산_확인(int value, int totalRepeat, int userRepeat) {
        Money money = new Money(value);
        Assertions.assertThat(money.createRepeatCount())
                .hasFieldOrPropertyWithValue("repeatCount", totalRepeat);
    }
}
