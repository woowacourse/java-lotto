package domain;

import lotto.domain.RandomNumberGenerator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class RandomNumberGeneratorTest {
    @Test
    void generate_범위_안에_랜덤_값을_도출하는지_확인() {
        RandomNumberGenerator randomNumberGenerator = new RandomNumberGenerator();
        Assertions.assertThat(randomNumberGenerator.generate().getValue())
                .isBetween(1, 45);
    }

    @Test
    void generates_로또_번호의_수가_맞게_도출하는지_확인() {
        RandomNumberGenerator randomNumberGenerator = new RandomNumberGenerator();
        Assertions.assertThat(randomNumberGenerator.generateNumbers().size())
                .isEqualTo(6);
    }
}
