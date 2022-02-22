package lotto.domain;

import java.util.List;
import java.util.stream.IntStream;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class NumberGeneratorTest {

    @Test
    @DisplayName("랜덤으로 번호를 생성한다.")
    public void createRandomNumber() {
        // given
        NumberGenerator numberGenerator = new RandomNumberGenerator(1, 45);
        // when
        List<Integer> numbers = numberGenerator.generate(6);
        // then
        Assertions.assertThat(numbers.size()).isEqualTo(6);
    }

    @Test
    @DisplayName("번호는 0 이상 45 이하의 값을 생성한다.")
    public void testRandomNumberRange() {
        // given
        NumberGenerator numberGenerator = new RandomNumberGenerator(1, 45);
        // when
        List<Integer> numbers = numberGenerator.generate(6);
        // then
        Assertions.assertThat(IntStream.range(1, 46)
                .boxed()
                .toArray(Integer[]::new))
            .contains(numbers.toArray(Integer[]::new));
    }
}
