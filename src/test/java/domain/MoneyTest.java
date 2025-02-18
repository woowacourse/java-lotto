package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class MoneyTest {

    @Test
    @DisplayName("Money 객체는 int 타입의 정수로 생성할 수 있다")
    void Money_객체는_int_타입의_정수로_생성할_수_있다() {
        // given
        int money = 1234567;

        // when
        // then
        assertThatCode(() -> Money.from(money))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("돈의 값은 0이 될 수 있다")
    void 돈의_값은_0이_될_수_있다() {
        // given
        int money = 0;

        // when
        // then
        assertThatCode(() -> Money.from(money))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("돈의 값은 음수가 될 수 없다")
    void 돈의_값은_음수가_될_수_없다() {
        // given
        int money = -1;

        // when
        // then
        assertThatThrownBy(() -> Money.from(money))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("돈의 값은 음수가 될 수 없습니다. 입력된 값: %d", money);
    }
}
