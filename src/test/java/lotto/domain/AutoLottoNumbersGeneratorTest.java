package lotto.domain;

import lotto.domain.generator.AutoLottoNumbersGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AutoLottoNumbersGeneratorTest {
    AutoLottoNumbersGenerator autoLottoNumbersGenerator;

    @BeforeEach
    void setUp() {
        autoLottoNumbersGenerator = AutoLottoNumbersGenerator.getInstance();
    }

    @Test
    void 번호들이_셔플되어_나오는지_확인() {
        assertThat(autoLottoNumbersGenerator.generate())
                .isNotEqualTo(autoLottoNumbersGenerator.generate());
    }
}
