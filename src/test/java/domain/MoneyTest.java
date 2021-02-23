package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class MoneyTest {
    @DisplayName("같은 금액의 Money객체는 equals에 true를 반환한다.")
    @Test
    public void equalsTest() {
        Money money1 = new Money(1000);
        Money money2 = new Money(1000);
        assertThat(money1).isEqualTo(money2);
    }

    @DisplayName("숫자가 아닌 인자를 생성자에 넘길 경우 예외를 발생시킨다.")
    @Test
    public void validateMoneyFormatTest() {
        assertThatThrownBy(() -> new Money("a")).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("음수가 입력된 경우 예외를 발생시킨다.")
    @Test
    public void validatePositiveNumber() {
        assertThatThrownBy(() -> new Money(-1)).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("연산 함수는 새로운 Money객체를 리턴한다. - 뺄셈")
    @Test
    public void minusTest() {
        Money money = new Money(2000);
        assertThat(money.subtract(new Money(1000))).isEqualTo(new Money(1000));
    }

    @DisplayName("0원 이하로 떨어지는 뺄셈을 할 수 없다. 이 경우, 예외가 발생한다.")
    @Test
    public void minusErrorTest() {
        Money money = new Money(2000);
        assertThatThrownBy(() -> money.subtract(new Money(3000))).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("연산 함수는 새로운 Money객체를 리턴한다. - 곱셈")
    @Test
    public void multiplyTest() {
        Money money = new Money(1000);
        assertThat(money.multiply(10)).isEqualTo(new Money(10000));
    }
}
