package domain;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class NumbersTest {

    @DisplayName("로또 번호에 중복이 있으면 예외가 발생한다.")
    @Test
    void duplicateNumbers() {
        assertThatThrownBy(() -> Numbers.from(List.of(1, 2, 3, 4, 5, 5)))
            .isExactlyInstanceOf(
                IllegalArgumentException.class)
            .hasMessage("로또 번호는 중복되면 안됩니다.");
    }

    @DisplayName("로또 번호가 6개가 아니라면 예외가 발생한다.")
    @Test
    void lessThanNumbersSize() {
        //given //when //then
        assertThatThrownBy(() -> Numbers.from(List.of(1, 2, 3, 4, 5))).isExactlyInstanceOf(
            IllegalArgumentException.class).hasMessage("로또 번호는 6개가 되어야 합니다.");
    }
    
    @DisplayName("로또 번호가 6자리고 중복이 없다면 정상 동작 한다.")
    @Test
    void nonDuplicateAndLessThanNumberSize() {
        //given //when //then
        assertThatCode(() -> Numbers.from(List.of(1, 2, 3, 4, 5, 6)))
            .doesNotThrowAnyException();
    }
}
