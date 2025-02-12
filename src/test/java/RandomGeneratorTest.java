import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


class RandomGeneratorTest {
    @Test
    @DisplayName("난수 값 6개 생성 테스트")
    void 난수_값_6개_생성_테스트() {
        List<Integer> numbers = RandomGenerator.generateUniqueRandomNumbers(6, 1, 45);
        Assertions.assertThat(numbers.size()).isEqualTo(6);
    }

    @Test
    @DisplayName("값들의 범위 테스트")
    void 값들의_범위_테스트() {

        List<Integer> numbers = RandomGenerator.generateUniqueRandomNumbers(6, 1, 45);

        for (int number : numbers) {
            assertTrue(number >= 1 && number <= 45, "숫자가 범위를 벗어났습니다: " + number);
        }
    }

    @Test
    @DisplayName("값들의 중복 테스트")
    void 값들의_중복_테스트() {

        List<Integer> numbers = RandomGenerator.generateUniqueRandomNumbers(6, 1, 45);

        assertEquals(numbers.size(), numbers.stream().distinct().count(), "중복된 숫자가 생성되었습니다.");
    }
}