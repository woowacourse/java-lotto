import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import util.RandomGenerator;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


class RandomGeneratorTest {
    @Test
    @DisplayName("난수 값 6개 생성 테스트")
    void 난수_값_6개_생성_테스트() {
        // given
        int count = 6;

        // when
        List<Integer> numbers = RandomGenerator.generateUniqueRandomNumbers(count, 1, 45);

        // then
        assertThat(numbers.size()).isEqualTo(count);
    }

    @Test
    @DisplayName("값들의 범위 테스트")
    void 값들의_범위_테스트() {
        // given
        int start = 1;
        int end = 45;
        int count = 6;

        // when
        List<Integer> numbers = RandomGenerator.generateUniqueRandomNumbers(count, start, end);

        // then
        for (int number : numbers) {
            assertThat(number).isBetween(1, 45);
        }
    }

    @Test
    @DisplayName("값들의 중복 테스트")
    void 값들의_중복_테스트() {
        // given
        int start = 1;
        int end = 45;
        int count = 6;

        // when
        List<Integer> numbers = RandomGenerator.generateUniqueRandomNumbers(count, start, end);

        // then
        assertThat(numbers.size()).
                isEqualTo(numbers.stream().distinct().count());
    }
}
