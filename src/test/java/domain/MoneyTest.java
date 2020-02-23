package domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class MoneyTest {
    @DisplayName("Money 생성자 테스트")
    @ParameterizedTest
    @ValueSource(ints = {999, -1})
    void moneyConstructorTest(int input) {
        Assertions.assertThatThrownBy(() -> {
            new Money(input);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("사용자가 입력한 값을 1_000원으로 나눈 결과값 테스트")
    @ParameterizedTest
    @ValueSource(ints = {14000, 14100, 14010})
    void getLottoTicketCountTest(int input) {
        Money money = new Money(input);
        int expected = 14;
        Assertions.assertThat(money.getLottoTicketCount()).isEqualTo(expected);
    }
}
