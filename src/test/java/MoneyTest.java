import domain.Money;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class MoneyTest {

    @Test
    @DisplayName("Price 객체는 정수만 입력할 수 있다.")
    void Price_객체는_정수를_입력받을_수_있다() {
        // given
        String money = "10000";

        // when
        // then
        assertThatCode(() -> new Money(money))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("Price 객체는 정수만 입력할 수 있다.")
    void Price_객체는_정수가_아닌_값을_입력받을_수_없다() {
        // given
        String invalidFormat = "iWannaBeInteger";

        // when
        // then
        assertThatThrownBy(() -> new Money("invalidFormat"))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
