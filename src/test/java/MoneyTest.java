import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import domain.vo.Money;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MoneyTest {
    @DisplayName("음수이면 예외")
    @Test
    void test1() {
        // given
        final int input = -1000;

        // when & then
        assertThatThrownBy(()-> {
            new Money(input);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("0이면 예외")
    @Test
    void test2() {
        // given
        final int input = 0;

        // when & then
        assertThatThrownBy(()-> {
            new Money(input);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("숫자로 구성된 문자열일 때")
    @Test
    void test3() {
        // given
        String input = "1";

        // when
        Money money = Money.of(input);

        // then
        assertThat(money.getValue()).isEqualTo(1);
    }

    @DisplayName("숫자가 아닌 문자열일 때 예외")
    @Test
    void test4() {
        // given
        String input = "woowa";

        // when & then
        assertThatThrownBy(()-> {
            Money.of(input);
        }).isInstanceOf(IllegalArgumentException.class);
    }
}
