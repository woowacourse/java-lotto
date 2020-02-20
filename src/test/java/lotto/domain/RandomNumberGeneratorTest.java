package lotto.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class RandomNumberGeneratorTest {
    @Test
    @DisplayName("로또 번호가 6개 나오는지 확인")
    void generateNumbers() {
        RandomNumberGenerator randomNumberGenerator = new RandomNumberGenerator();
        Assertions.assertThat(randomNumberGenerator.generateNumbers().size())
                .isEqualTo(6);
    }
}
