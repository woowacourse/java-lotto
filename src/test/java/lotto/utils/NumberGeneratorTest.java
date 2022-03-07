package lotto.utils;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class NumberGeneratorTest {

    private NumberGenerator generator;

    @BeforeEach
    void setUp() {
        generator = new StringNumberGenerator("1,2,3,4,5,6");
    }

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
    void createStringNumber(int size) {
        // when
        List<Integer> numbers = generator.generate(size);
        // then
        assertThat(numbers.size()).isEqualTo(size);
    }

    @Test
    @DisplayName("입력된 숫자들의 길이가 요구하는 길이보다 작으면 예외를 던진다.")
    void validateNumbersSize() {
        // then
        assertThatThrownBy(() -> generator.generate(8))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("입력된 숫자들의 길이가 요구하는 길이보다 작습니다.");
    }

    @Test
    @DisplayName("더 이상 읽어들인 숫자들이 존재하지 않으면 예외를 던진다.")
    void validateReadLine() {
        // when
        generator.generate(2);
        // then
        assertThatThrownBy(() -> generator.generate(2))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("읽어들일 숫자가 존재하지 않습니다.");
    }
}
