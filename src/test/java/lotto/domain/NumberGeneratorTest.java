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

    @Test
    @DisplayName("줄바꿈을 기준으로 여러 줄의 숫자를 생성할 수 있다.")
    public void createMultipleLinesByNewLineDelimiter() {
        // given
        NumberGenerator generator =
            new StringInputNumberGenerator(List.of("1, 2, 3, 4, 5, 6", "7, 8, 9, 10, 11, 12"));

        // when
        List<Integer> firstLine = generator.generate(3);
        List<Integer> secondLine = generator.generate(4);
        // then
        Assertions.assertAll(
            () -> assertThat(firstLine).containsSequence(1, 2, 3),
            () -> assertThat(secondLine).containsSequence(7, 8, 9, 10)
        );
    }

    @Test
    @DisplayName("문자열 라인 소진시 예외를 던진다.")
    public void throwsExceptionAfterConsumptionOfLine() {
        // given
        NumberGenerator generator = new StringInputNumberGenerator("1, 2, 3, 4, 5, 6");

        // when
        generator.generate(3);

        // then
        assertThatExceptionOfType(IllegalArgumentException.class)
            .isThrownBy(() -> generator.generate(3));
    }

    @Test
    @DisplayName("저장된 숫자 개수보다 더 큰 길이를 요구하면 예외를 던진다.")
    public void throwsExceptionWhenSizeTooBig() {
        // when
        NumberGenerator generator = new StringInputNumberGenerator("1, 2, 3, 4, 5, 6");

        // then
        assertThatExceptionOfType(IllegalArgumentException.class)
            .isThrownBy(() -> generator.generate(7));
    }
}
