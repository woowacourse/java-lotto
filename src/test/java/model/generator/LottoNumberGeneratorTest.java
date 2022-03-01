package model.generator;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoNumberGeneratorTest {

    @Test
    @DisplayName("생성한 난수들이 1~45의 숫자인지 확인한다.")
    void generateNumbers_Range() {
        final Generator generator = new LottoNumberGenerator();
        final List<Integer> numbers = generator.generateNumbers();

        for (int number : numbers) {
            assertThat(number).isGreaterThanOrEqualTo(1);
            assertThat(number).isLessThanOrEqualTo(45);
        }
    }
}