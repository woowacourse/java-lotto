import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.Test;

class RandomNumbersGeneratorTest {
    @Test
    void 중복되지_않는_숫자_리스트가_생성된다() {
        // given
        final int start = 1;
        final int end = 3;
        final int count = 3;

        // when
        List<Integer> numbers = RandomNumbersGenerator.generateUniqueNumbers(start, end, count);

        // then
        assertThat(numbers).containsExactly(1, 2, 3);
    }

    @Test
    void 범위가_올바르지_않는_경우_예외() {
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