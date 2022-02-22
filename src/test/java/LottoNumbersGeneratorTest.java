import domain.LottoNumbersGenerator;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class LottoNumbersGeneratorTest {

    @Test
    void generatedLottoNumbersSize() {
        assertThat(LottoNumbersGenerator.generate().size()).isEqualTo(6);
    }

    @Test
    void generatedLottoNumbersDistinct() {
        long size = LottoNumbersGenerator.generate().stream()
                .distinct()
                .count();

        assertThat(size).isEqualTo(6);
    }
}
