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
        assertThatCode(() -> new Money("10000"))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("Price 객체는 정수만 입력할 수 있다.")
    void Price_객체는_정수가_아닌_값을_입력받을_수_없다() {
        assertThatThrownBy(() -> new Money("asdasd"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("Price getter")
    void getterTest() {
        Money money = new Money("10000");

        assertThat(money.getAmount()).isEqualTo(10000);
    }

}