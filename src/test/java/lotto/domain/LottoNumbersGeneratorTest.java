package lotto.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoNumbersGeneratorTest {
    @Test
    void 번호들이_셔플되어_나오는지_확인() {
        assertThat(LottoNumbersGenerator.getLottoNumbers()).isNotEqualTo(LottoNumbersGenerator.getLottoNumbers());
    }
}
