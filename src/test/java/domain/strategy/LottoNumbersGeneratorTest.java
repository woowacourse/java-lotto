package domain.strategy;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class LottoNumbersGeneratorTest {

    private final LottoNumberStrategy numberGenerator = new RandomLottoNumberStrategy();

    @Test
    void generatedLottoNumbersSize() {
        assertThat(numberGenerator.generate().size()).isEqualTo(6);
    }

    @Test
    void generatedLottoNumbersDistinct() {
        long size = numberGenerator.generate().stream()
                .distinct()
                .count();

        assertThat(size).isEqualTo(6);
    }
}
