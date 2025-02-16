import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RandomNumbersGeneratorTest {

    @Test
    @DisplayName("시작과 끝 범위, 개수를 사용하여 해당 범위 내의 숫자 리스트를 생성하는지 확인한다")
    void check_generated_numbers_is_within_range_correctly() {
        // given
        final int start = 1;
        final int end = 3;
        final int count = 2;

        // when
        List<Integer> numbers = RandomNumbersGenerator.generateUniqueNumbers(start, end, count);

        // then
        assertThat(numbers).containsAnyOf(1, 2, 3);
    }

    @Test
    @DisplayName("시작과 끝 범위, 개수를 사용하여 해당 범위 내의 숫자 리스트를 생성하는지 확인한다")
    void check_generated_number_count_is_correct_count_correctly() {
        // given
        final int start = 1;
        final int end = 3;
        final int count = 2;

        // when
        List<Integer> numbers = RandomNumbersGenerator.generateUniqueNumbers(start, end, count);

        // then
        assertThat(numbers).hasSize(2);
    }

    @Test
    @DisplayName("시작과 끝 범위로 생성 가능한 숫자의 범위가 생성할 개수보다 적을 때 예외가 발생하는지 확인한다")
    void exception_when_range_is_smaller_than_count() {
        // given
        final int start = 1;
        final int end = 3;
        final int count = 4;

        // when & then
        assertThatThrownBy(() -> {
            RandomNumbersGenerator.generateUniqueNumbers(start, end, count);
        }).isInstanceOf(IllegalArgumentException.class);
    }
}