package lotto.domain;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoNumbersGeneratorTest {
    @Test
    void 번호들이_셔플되어_나오는지_확인() {
        assertThat(LottoNumbersGenerator.getLottoNumbers())
                .isNotEqualTo(LottoNumbersGenerator.getLottoNumbers());
    }

    @Test
    void 입력한_번호를_제대로_생성하는지_확인() {
        LottoNumbers lottoNumbers = new LottoNumbers(Arrays.asList(
                new LottoNumber(1),
                new LottoNumber(2),
                new LottoNumber(3),
                new LottoNumber(4),
                new LottoNumber(5),
                new LottoNumber(6)));

        assertThat(LottoNumbersGenerator.getLottoNumbers("1,2,3,4,5,6")).isEqualTo(lottoNumbers);
    }
}
