package lotto.domain;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class LottoNumberGeneratorTest {
    @Test
    void checkSize() {
        LottoNumberGenerator lottoNumberGenerator = new LottoNumberGenerator();
        Lotto lotto = lottoNumberGenerator.createLotto();

        assertThat(lotto.getLottoSize()).isEqualTo(6);
    }
}