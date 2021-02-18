package lotto.domain;

import lotto.util.LottoGenerator;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class LottoGeneratorTest {
    @Test
    void check_six() {
        List<Integer> lottoNumbers = LottoGenerator.make();

        assertThat(lottoNumbers.size()).isEqualTo(6);
    }
}