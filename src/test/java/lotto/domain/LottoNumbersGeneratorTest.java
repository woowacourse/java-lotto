package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;

class LottoNumbersGeneratorTest {

    @Test
    void generate() {
        //when
        List<Integer> lottoNumbers = LottoNumbersGenerator.generate();
        //then
        assertThat(lottoNumbers.size()).isEqualTo(LottoNumberConfig.SIZE);
    }
}
