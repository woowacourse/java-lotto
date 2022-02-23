package model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

import java.util.Set;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

public class RandomDistinctNumberQueueTest {

    @ParameterizedTest
    @MethodSource("provideRangeAndSizeAndExpectedSet")
    @DisplayName("랜덤 값 생성하기")
    void createRandomNumbers(int start, int end, int size, Set<Integer> expectedSet) {
        NumberQueue randomNumberQueue = new RandomDistinctNumberQueue(start, end);
        Set<Integer> numberSet = randomNumberQueue.get(size);

        assertThat(numberSet).hasSize(size);
        assertThat(numberSet).isEqualTo(expectedSet);
    }

    private static Stream<Arguments> provideRangeAndSizeAndExpectedSet() {
        return Stream.of(
            Arguments.of(1, 6, 6, Set.of(1, 2, 3, 4, 5, 6)),
            Arguments.of(10, 12, 3, Set.of(10, 11, 12)),
            Arguments.of(6, 10, 5, Set.of(6, 7, 8, 9, 10))
        );
    }
}
