package lotto.domain.number;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class NumberTest {

    @Test
    @DisplayName("숫자가 아닌 경우")
    void inputNoNumber() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new Number("abc"))
                .withMessageContaining("입력이 숫자가 아니거나");
    }

    @Test
    @DisplayName("Integer 범위 밖인 경우")
    void inputOutOfInteger() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new Number("9876543211324"))
                .withMessageContaining("범위를 벗어났습니다.");
    }

    @Test
    @DisplayName("정상적인 숫자 생성")
    void inputNormal() {
        Number number = new Number(1);
        assertThat(number.equals(1)).isTrue();

        Number numberByString = new Number("1");
        assertThat(numberByString.equals(1)).isTrue();
    }

    @Test
    @DisplayName("덧셈을 하면 더해진 수의 Number 객체를 반환한다.")
    void add() {
        Number numberOne = new Number(1);
        assertThat(numberOne.add(numberOne)).isEqualTo(new Number(2));
    }

    @Test
    @DisplayName("뺄셈을 하면 빼진 수의 Number 객체를 반환한다.")
    void subtract() {
        Number numberTwo = new Number(2);
        Number numberOne = new Number(1);

        assertThat(numberTwo.subtract(numberOne)).isEqualTo(numberOne);
    }
}