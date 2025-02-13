package domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoTest {
    @Test
    @DisplayName("숫자가 1에서 45사이가 아니므로 예외가 발생한다")
    void validateRangeTest() {
        //given
        final List<Integer> numbers1 = List.of(1, 2, 3, 4, 5, 0);
        final List<Integer> numbers2 = List.of(1, 2, 3, 4, 5, 46);
        //when
        //then
        assertAll(() -> assertThatThrownBy(() -> new Lotto(numbers1)).isInstanceOf(IllegalArgumentException.class),
                () -> assertThatThrownBy(() -> new Lotto(numbers2)).isInstanceOf(IllegalArgumentException.class));
    }

    @Test
    @DisplayName("중복된 숫자가 존재하여 예외가 발생한다")
    void validateDuplicateTest() {
        //given
        final List<Integer> numbers1 = List.of(1, 2, 3, 4, 5, 5);
        final List<Integer> numbers2 = List.of(1, 1, 2, 3, 4, 5);
        //when
        //then
        assertAll(() -> assertThatThrownBy(() -> new Lotto(numbers1)).isInstanceOf(IllegalArgumentException.class),
                () -> assertThatThrownBy(() -> new Lotto(numbers2)).isInstanceOf(IllegalArgumentException.class));
    }

    @Test
    @DisplayName("당첨 번호가 6개가 아니므로 예외가 발생한다")
    void validateSizeTest() {
        //given
    	final List<Integer> numbers1 = List.of(1, 2, 3, 4, 5);
        final List<Integer> numbers2 = List.of(1, 2, 3, 4, 5, 6, 7);
        //when
        //then
        assertAll(() -> assertThatThrownBy(() -> new Lotto(numbers1)).isInstanceOf(IllegalArgumentException.class),
                () -> assertThatThrownBy(() -> new Lotto(numbers2)).isInstanceOf(IllegalArgumentException.class));
    }
}
