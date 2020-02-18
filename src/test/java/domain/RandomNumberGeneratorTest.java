package domain;

import lotto.domain.RandomNumberGenerator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class RandomNumberGeneratorTest {
    @Test
    void 범위_안에_랜덤_값을_도출하는지_확인() {
        RandomNumberGenerator randomNumberGenerator = new RandomNumberGenerator();
        Assertions.assertThat(randomNumberGenerator.generate())
                .isBetween(1, 45);
    }
}
