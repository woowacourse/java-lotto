package lotto.domain;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import lotto.utils.NumberGenerator;
import lotto.utils.RandomNumberGenerator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class NumberGeneratorTest {

    @Test
    @DisplayName("랜덤으로 여러 번호를 생성한다.")
    void createRandomNumber() {
        // given
        NumberGenerator numberGenerator = new RandomNumberGenerator(1, 10);
        // when
        List<Integer> numbers = numberGenerator.generate(6);
        // then
        Assertions.assertThat(numbers.size()).isEqualTo(6);
    }

    @Test
    @DisplayName("임의의 범위의 값을 생성한다.")
    void testRandomNumberRange() {
        // given
        NumberGenerator numberGenerator = new RandomNumberGenerator(1, 10);
        // when
        List<Integer> numbers = numberGenerator.generate(6);
        // then
        Assertions.assertThat(IntStream.rangeClosed(1, 10)
                .boxed()
                .collect(Collectors.toList()))
            .contains(numbers.toArray(Integer[]::new));
    }
}
