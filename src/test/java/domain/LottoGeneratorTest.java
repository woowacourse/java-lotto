package domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

class LottoGeneratorTest {
    @DisplayName("6개의 숫자를 중복 없이 생성한다.")
    @RepeatedTest(10)
    void duplicateTest() {
        final List<Integer> generatedNumbers = LottoGenerator.generate();

        assertThat(generatedNumbers).hasSize(6).doesNotHaveDuplicates();
    }

    @DisplayName("1부터 45 이내의 숫자만 생성한다")
    @Test
    void InRangeTest() {
        final List<Integer> generatedNumbers = LottoGenerator.generate();

        for (Integer number : generatedNumbers) {
            assertThat(number).isBetween(1, 45);
        }
    }
}
