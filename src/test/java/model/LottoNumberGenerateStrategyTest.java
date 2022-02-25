package model;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoNumberGenerateStrategyTest {

    @Test
    @DisplayName("번호 생성기가 1~45까지의 번호를 생성하는지 확인한다.")
    void generateNumbers_Test() {
        final LottoNumberGenerateStrategy lottoNumberGenerateStrategy = new LottoNumberGenerateStrategy();
        final List<Integer> generatedNumbers = lottoNumberGenerateStrategy.generateNumbers();

        generatedNumbers.forEach(generatedNumber -> {
            assertThat(generatedNumber).isGreaterThanOrEqualTo(1);
            assertThat(generatedNumber).isLessThanOrEqualTo(45);
        });
    }
}