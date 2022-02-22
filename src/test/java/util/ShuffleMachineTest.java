package util;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.Test;

public class ShuffleMachineTest {

    @Test
    public void validateGeneratedNumberCount() {
        List<Integer> generatedNumbers = ShuffleMachine.generate();
        assertThat(generatedNumbers.size()).isEqualTo(6);
    }
}
