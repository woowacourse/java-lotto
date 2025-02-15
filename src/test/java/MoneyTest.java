import domain.Money;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class MoneyTest {

    @Test
    @DisplayName("Money 객체는 String 타입의 정수로 생성할 수 있다")
    void Money_객체는_String_타입의_정수로_생성할_수_있다() {
        // given
        String money = "10000";

        // when
        // then
        assertThatCode(() -> new Money(money))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("Money 객체는 String 타입의 정수가 아니라면 예외를 던진다")
    void Money_객체는_String_타입의_정수가_아니라면_예외를_던진다() {
        // given
        String invalidFormat = "iWannaBeInteger";

        // when
        // then
        assertThatThrownBy(() -> new Money(invalidFormat))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
