package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import lotto.domain.generator.NumberGenerator;
import lotto.domain.generator.RandomNumberGenerator;
import lotto.domain.generator.StringInputNumberGenerator;

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
    void testRandomNumberGeneration() {
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

    @Test
    @DisplayName("문자열을 기반으로 값을 생성한다.")
    public void testStringInputNumberGeneration() {
        // given
        NumberGenerator generator = new StringInputNumberGenerator("1, 2, 3, 4, 5, 6");
        // when
        List<Integer> numbers = generator.generate(3);
        // then
        Assertions.assertAll(
            () -> assertThat(numbers.size()).isEqualTo(3),
            () -> assertThat(numbers).contains(1, 2, 3)
        );
    }
}
