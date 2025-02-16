package lotto.model;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RandomNumberGeneratorTest {
    @DisplayName("랜덤하게 6개의 숫자를 반환한다.")
    @Test
    void generateWithValidNumberSize() {
        assertThat(RandomNumberGenerator.generate()).hasSize(6);
    }

}
