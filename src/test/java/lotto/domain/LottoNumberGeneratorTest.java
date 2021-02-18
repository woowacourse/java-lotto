package lotto.domain;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class LottoNumberGeneratorTest {
    @Test
    void check_six() {
        LottoNumberGenerator lottoNumberGenerator = new LottoNumberGenerator();
        List<Integer> lottoNumbers = lottoNumberGenerator.make();

        assertThat(lottoNumbers.size()).isEqualTo(6);
    }
}