package model;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoNumberGenerateStrategyTest {

    private final GenerateStrategy generateStrategy = new LottoNumberGenerateStrategy();

    @Test
    @DisplayName("LottoNumberGenerateStrategy가 1~45 사이의 숫자를 뽑는지 확인한다.")
    void checkGeneratedNumberRange() {
        List<Integer> numbers = generateStrategy.generateNumbers();
        numbers.forEach(number -> assertThat(number).isBetween(1, 45));
    }

    @Test
    @DisplayName("LottoNumberGenerateStrategy가 서로 다른 숫자를 뽑는지 확인한다.")
    void checkGeneratedNumberDuplicate() {
        List<Integer> numbers = generateStrategy.generateNumbers();
        numbers.forEach(number -> assertThat(Collections.frequency(numbers, number)).isEqualTo(1));
    }
}