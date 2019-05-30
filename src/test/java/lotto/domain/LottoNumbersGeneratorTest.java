package lotto.domain;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LottoNumbersGeneratorTest {
    @Test
    void 숫자_하나를_제대로_가져오는지_확인() {
        assertThat(LottoNumbersGenerator.getLottoNumber(1)).isEqualTo(new LottoNumber(1));
    }

    @Test
    void 번호들이_셔플되어_나오는지_확인() {
        assertThat(LottoNumbersGenerator.getLottoNumbers())
                .isNotEqualTo(LottoNumbersGenerator.getLottoNumbers());
    }

    @Test
    void 입력한_번호를_제대로_생성하는지_확인() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);

        LottoNumbers lottoNumbers = new LottoNumbers(Arrays.asList(
                new LottoNumber(1),
                new LottoNumber(2),
                new LottoNumber(3),
                new LottoNumber(4),
                new LottoNumber(5),
                new LottoNumber(6)));

        assertThat(LottoNumbersGenerator.getLottoNumbers(numbers)).isEqualTo(lottoNumbers);
    }

    @Test
    void 입력한_번호가_범위에서_벗어난_경우() {
        assertThrows(IllegalArgumentException.class, () ->
                LottoNumbersGenerator.getLottoNumber(0));
    }

    @Test
    void 입력한_번호들이_범위에서_벗어난_경우() {
        assertThrows(IllegalArgumentException.class, () ->
                LottoNumbersGenerator.getLottoNumbers(Arrays.asList(1, 2, 3, 4, 5, 46)));
    }
}
