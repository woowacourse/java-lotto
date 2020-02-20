package lotto;

import domain.RandomNumberGenerator;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class RandomNumberGeneratorTest {

    @Test
    void 숫자가_1부터_45까지_인지_검증(){
        int number = RandomNumberGenerator.generate();
        assertThat(number).isBetween(1, 45);
    }
}
