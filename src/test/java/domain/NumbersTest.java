package domain;

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

    @Test
    void 로또_번호가_6개가_아니면_예외가_발생한다() {

        assertThatThrownBy(() -> Numbers.from(List.of(1, 2, 3, 4, 5))).isExactlyInstanceOf(
            IllegalArgumentException.class).hasMessage("로또 번호는 6개가 되어야 합니다.");
    }

}
