import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class RandomGeneratorTest {
    @Test
    void 난수_값_6개_생성_테스트() {
        List<Integer> numbers = RandomGenerator.generateUniqueRandomNumbers(6, 1, 45);
        assertThat(numbers.size()).isEqualTo(6);
    }

    @Test
    @DisplayName("값들의 범위는 1에서 45이다")
    void 값들의_범위_테스트() {
        List<Integer> numbers = RandomGenerator.generateUniqueRandomNumbers(6, 1, 45);

        for (int number : numbers) {
            assertThat(number).isBetween(1, 45);
        }
    }

    @Test
    @DisplayName("값들은 중복되지 않아야한다")
    void 값들의_중복되지_않아야한다() {
        List<Integer> numbers = RandomGenerator.generateUniqueRandomNumbers(6, 1, 45);
        assertThat(numbers.size()).isEqualTo(numbers.stream().distinct().count());
    }
}
