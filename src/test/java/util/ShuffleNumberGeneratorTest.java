package util;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.Test;

public class ShuffleNumberGeneratorTest {

    @Test
    public void validateGeneratedNumberCount() {
        ShuffleNumberGenerator shuffleNumberGenerator = new ShuffleNumberGenerator();
        List<Integer> generatedNumbers = shuffleNumberGenerator.generate();
        assertThat(generatedNumbers.size()).isEqualTo(6);
    }
}
