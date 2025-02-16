package model;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoNumberGeneratorTest {

    @DisplayName("1~45사이의 6개의 무작위 정수를 생성한다")
    @Test
    void lottoGenerateTest() {
        LottoNumberGenerator lottoNumberGenerator = new LottoNumberGenerator();
        Set<Integer> numbers = lottoNumberGenerator.generateNumbers();

        assertThat(numbers.size()).isEqualTo(6);
        for (int number : numbers) {
            assertThat(number).isBetween(1, 45);
        }
    }
}