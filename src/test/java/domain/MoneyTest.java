package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatCode;

class MoneyTest {

    @Test
    @DisplayName("Money 객체는 int 타입의 정수로 생성할 수 있다")
    void Money_객체는_int_타입의_정수로_생성할_수_있다() {
        // given
        int money = 10000;

        // when
        // then
        assertThatCode(() -> Money.from(money))
                .doesNotThrowAnyException();
    }
}
