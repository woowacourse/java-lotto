import model.RandomGenerator;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class RandomGeneratorTest {
    @Test
    void 난수_값_6개_생성_테스트() {
        //when & then
        List<Integer> numbers = RandomGenerator.generateUniqueRandomNumbers(6, 1, 45);
        assertThat(numbers.size()).isEqualTo(6);
    }

    @Test
    void 값들의_범위_테스트() {
        //when & then
        List<Integer> numbers = RandomGenerator.generateUniqueRandomNumbers(6, 1, 45);

        for (int number : numbers) {
            assertThat(number).isBetween(1, 45);
        }
    }

    @Test
    void 값들의_중복되지_않아야한다() {
        //when & then
        List<Integer> numbers = RandomGenerator.generateUniqueRandomNumbers(6, 1, 45);
        assertThat(numbers.size()).isEqualTo(numbers.stream().distinct().count());
    }
}
