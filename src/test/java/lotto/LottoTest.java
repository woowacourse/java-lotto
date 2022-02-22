package lotto;

import static org.assertj.core.api.Assertions.*;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoTest {

    @Test
    @DisplayName("로또 번호는 6자리 숫자로 생성된다.")
    void createSixSizeNumbers() {
        List<Number> numbers = List.of(number(1), number(2), number(3), number(4), number(5), number(6));

        assertThat(new Lotto(numbers)).isNotNull();
    }

    @Test
    @DisplayName("로또 번호는 5자리 숫자가 전달되면 예외가 발생한다.")
    void throwExceptionWhenFiveSizeNumbers() {
        List<Number> numbers = List.of(number(1), number(2), number(3), number(4), number(5));

        assertThatIllegalArgumentException()
            .isThrownBy(() -> new Lotto(numbers))
            .withMessageMatching("로또 번호는 6자리 이어야 한다.");
    }

    @Test
    @DisplayName("로또 번호는 7자리 숫자가 전달되면 예외가 발생한다.")
    void throwExceptionWhenSevenSizeNumbers() {
        List<Number> numbers = List.of(number(1), number(2), number(3), number(4), number(5), number(6), number(7));

        assertThatIllegalArgumentException()
            .isThrownBy(() -> new Lotto(numbers))
            .withMessageMatching("로또 번호는 6자리 이어야 한다.");
    }

    private Number number(int number) {
        return new Number(number);
    }
}