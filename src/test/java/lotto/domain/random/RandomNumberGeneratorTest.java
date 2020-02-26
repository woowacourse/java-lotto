package lotto.domain.random;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class RandomNumberGeneratorTest {
    @Test
    @SuppressWarnings("NonAsciiCharacters")
    void generate_범위_안에_랜덤_값을_도출하는지_확인() {
        RandomNumberGenerator randomNumberGenerator = new RandomNumberGenerator();
        for (int i = 0; i < 45; i++) {
            int j = randomNumberGenerator.generate().getNumber();
            Assertions.assertThat(j)
                    .isBetween(1, 45);
        }
    }

    @Test
    @SuppressWarnings("NonAsciiCharacters")
    void generates_로또_번호의_수가_맞게_도출하는지_확인() {
        RandomNumberGenerator randomNumberGenerator = new RandomNumberGenerator();
        Assertions.assertThat(randomNumberGenerator.generateNumbers())
                .hasSize(6);
    }
}
