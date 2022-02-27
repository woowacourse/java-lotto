package util;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ShuffleNumberGeneratorTest {

    @Test
    @DisplayName("만들어지는 수들이 6개인지 확인 테스트")
    public void validateGeneratedNumberCount() {
        ShuffleNumberGenerator shuffleNumberGenerator = new ShuffleNumberGenerator();
        List<Integer> generatedNumbers = shuffleNumberGenerator.generate();
        assertThat(generatedNumbers.size()).isEqualTo(6);
    }
}
