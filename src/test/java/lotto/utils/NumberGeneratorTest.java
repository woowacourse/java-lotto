package lotto.utils;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class NumberGeneratorTest {

    @DisplayName("랜덤 숫자의 크기가 올바르게 나와야 한다.")
    @Test
    void 랜덤_숫자의_크기가_올바르게_나와야_한다() {

        int makeQuantity = 6;
        int minValue = 1;
        int maxValue = 45;
        List<Integer> randomNumbers = NumberGenerator.numberGeneratorWithUniqueValues(makeQuantity, minValue, maxValue);

        assertThat(randomNumbers.size()).isEqualTo(makeQuantity);
    }

    @DisplayName("랜덤 숫자는 중복되면 안된다.")
    @Test
    void 랜덤_숫자는_중복되면_안된다() {

        int makeQuantity = 6;
        int minValue = 1;
        int maxValue = 45;
        List<Integer> randomNumbers = NumberGenerator.numberGeneratorWithUniqueValues(makeQuantity, minValue, maxValue);

        assertThat(new HashSet<>(randomNumbers)).hasSize(makeQuantity);
    }

    @DisplayName("랜덤 숫자는 정렬되어 나와야 한다.")
    @Test
    void 랜덤_숫자는_정렬되어_나와야_한다() {

        int makeQuantity = 6;
        int minValue = 1;
        int maxValue = 45;
        List<Integer> randomNumbers = NumberGenerator.numberGeneratorWithUniqueValues(makeQuantity, minValue, maxValue);
        List<Integer> sortedRandomNumbers = new ArrayList<>(randomNumbers);
        Collections.sort(sortedRandomNumbers);

        assertThat(randomNumbers).isEqualTo(sortedRandomNumbers);
    }

    @DisplayName("최대 및 최소 설정값대로 나와야 한다.")
    @Test
    void 최대_및_최소_설정값대로_나와야_한다() {

        int minValue = 1;
        int maxValue = 45;
        int makeQuantity = maxValue - minValue + 1;

        List<Integer> randomNumbers = NumberGenerator.numberGeneratorWithUniqueValues(makeQuantity, minValue, maxValue);
        assertThat(randomNumbers).allMatch(num -> num >= minValue && num <= maxValue);
    }
}
