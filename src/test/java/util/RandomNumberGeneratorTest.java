package util;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class RandomNumberGeneratorTest {

    @DisplayName("6개의 숫자를 생성한다.")
    @Test
    void makeSixNumbers() {
        // when & then
        List<Integer> numbers = RandomNumberGenerator.getRandomNumbers(1, 45);
        assertThat(numbers.size()).isEqualTo(6);
    }

    @DisplayName("6개의 숫자는 중복이 없어야한다.")
    @Test
    void makeNotDuplicatedNumbers() {
        // when
        List<Integer> numbers = RandomNumberGenerator.getRandomNumbers(1, 45);
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
        List<Integer> numbers = RandomNumberGenerator.getRandomNumbers(min, max);
        // then
        for (int i = 0; i < 6; i++) {
            assertThat(numbers.get(i)).isLessThanOrEqualTo(max).isGreaterThanOrEqualTo(min);
        }
    }
}