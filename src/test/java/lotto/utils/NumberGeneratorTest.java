package lotto.utils;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class NumberGeneratorTest {

    @DisplayName("랜덤 숫자의 크기가 올바르게 나와야 한다.")
    @Test
    void 랜덤_숫자의_크기가_올바르게_나와야_한다() {

        //given
        List<Integer> randomNumbers = NumberGenerator.numberGeneratorWithUniqueValues(6, 1, 45);

        //when & then
        assertThat(randomNumbers.size()).isEqualTo(6);
    }

    @DisplayName("최대 및 최소 설정값대로 나와야 한다.")
    @Test
    void 최대_및_최소_설정값대로_나와야_한다() {

        //given
        List<Integer> randomNumbers = NumberGenerator.numberGeneratorWithUniqueValues(1, 1, 1);

        //when & then
        assertThat(randomNumbers.getFirst()).isEqualTo(1);
    }

}
