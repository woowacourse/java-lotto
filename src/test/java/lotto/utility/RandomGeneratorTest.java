package lotto.utility;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class RandomGeneratorTest {

    @DisplayName("입력한 최대 범위까지의 난수 리스트를 생성한다.")
    @Test
    void 입력한_최대_범위까지의_난수_리스트를_생성한다() {
        RandomGenerator randomGenerator = new RandomGenerator();
        final int MAX_NUMBER = 45;
        List<Integer> randomNumbers = randomGenerator.generateUniqueRandomNumbers(MAX_NUMBER);

        assertThat(randomNumbers).allSatisfy(number -> assertThat(number).isStrictlyBetween(0, 46));
    }
}
