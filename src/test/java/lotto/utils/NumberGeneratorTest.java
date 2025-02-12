package lotto.utils;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class NumberGeneratorTest {

    @DisplayName("랜덤 숫자의 범위가 올바르게 나와야 한다.")
    @Test
    void 랜덤_숫자의_범위가_올바르게_나와야_한다() {

        int randomNumber = NumberGenerator.numberGenerator(1, 45);

        assertThat(randomNumber).isGreaterThanOrEqualTo(1);
        assertThat(randomNumber).isLessThanOrEqualTo(45);
    }

}
