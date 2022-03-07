package lotto.utils;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class NumberGeneratorTest {

    @Test
    @DisplayName("랜덤으로 여러 번호를 생성한다.")
    void createRandomNumber() {
        // given
        NumberGenerator numberGenerator = new RandomNumberGenerator(1, 10);
        // when
        List<Integer> numbers = numberGenerator.generate(6);
        // then
        assertThat(numbers.size()).isEqualTo(6);
    }

    @Test
    @DisplayName("임의의 범위의 값을 생성한다.")
    void testRandomNumberRange() {
        // given
        NumberGenerator numberGenerator = new RandomNumberGenerator(1, 10);
        // when
        List<Integer> numbers = numberGenerator.generate(6);
        // then
        assertThat(IntStream.rangeClosed(1, 10)
                .boxed()
                .collect(Collectors.toList()))
            .contains(numbers.toArray(Integer[]::new));
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5, 6})
    @DisplayName("문자열을 입력받아 정상적으로 생성되는지 확인")
    public void createStringNumber(int size) {
        // given
        NumberGenerator generator = new StringNumberGenerator("1,2,3,4,5,6");
        // when
        List<Integer> numbers = generator.generate(size);
        // then
        assertThat(numbers.size()).isEqualTo(size);
    }
}
