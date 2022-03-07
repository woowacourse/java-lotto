package generator;

import static org.assertj.core.api.Assertions.assertThat;

import model.generator.LottosGenerator;
import model.generator.RandomGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RandomGeneratorTest {

    private LottosGenerator randomGenerator;

    @BeforeEach
    void setUp() {
        randomGenerator = new RandomGenerator(3);
    }

    @Test
    void generateRandom() {
        assertThat(randomGenerator.createLottos()).hasSize(3);
    }
}