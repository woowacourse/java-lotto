package lotto.utility;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RandomGeneratorTest {

    @DisplayName("범위 내의 중복되지 않는 랜덤값을 주어진 개수만큼 구할 수 있다.")
    @Test
    public void 범위_내의_중복되지_않는_랜덤값을_주어진_개수만큼_구할_수_있다() {
        int expectedMax = 45;
        int expectedNumberCount = 10;
        RandomGenerator generator = new RandomGenerator();
        List<Integer> actualNumbers = generator.generateUniqueRandomNumbers(expectedMax, expectedNumberCount);

        assertThat(actualNumbers)
                .allMatch(number -> number >= 1 && number <= expectedMax)
                .doesNotHaveDuplicates()
                .hasSize(expectedNumberCount);
    }
}