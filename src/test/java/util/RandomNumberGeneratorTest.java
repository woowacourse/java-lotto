package util;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class RandomNumberGeneratorTest {

    private Random random = new Random();

    @DisplayName("6개의 숫자를 생성한다.")
    @Test
    void makeSixNumbers() {
        // given
        int min_value = 1;
        int max_value = 45;
        // when & then
        List<Integer> numbers = RandomNumberGenerator.getRandomNumbers(
                () -> random.nextInt(max_value - min_value + 1) + min_value);
        assertThat(numbers.size()).isEqualTo(6);
    }

    @DisplayName("6개의 숫자는 중복이 없어야한다.")
    @Test
    void makeNotDuplicatedNumbers() {
        // given
        int min_value = 1;
        int max_value = 45;
        // when
        List<Integer> numbers = RandomNumberGenerator.getRandomNumbers(
                () -> random.nextInt(max_value - min_value + 1) + min_value);
        // then
        Set<Integer> setNumbers = new HashSet<>(numbers);
        boolean isDuplicate = setNumbers.size() != numbers.size();
        assertThat(isDuplicate).isFalse();
    }

    @DisplayName("6개의 숫자는 지정된 범위 안의 숫자여야한다.")
    @ParameterizedTest
    @CsvSource({"1,45", "2,40"})
    void rangeTest(int min, int max) {
        // when
        List<Integer> numbers = RandomNumberGenerator.getRandomNumbers(
                () -> random.nextInt(max - min + 1) + min);
        // then
        for (int i = 0; i < 6; i++) {
            assertThat(numbers.get(i)).isLessThanOrEqualTo(max).isGreaterThanOrEqualTo(min);
        }
    }
}