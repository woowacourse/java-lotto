package lotto.domain;

import lotto.exception.OutOfLottoNumberBoundException;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LottoNumbersGeneratorTest {
    @Test
    void 번호들이_셔플되어_나오는지_확인() {
        assertThat(LottoNumbersGenerator.getLottoNumbers())
                .isNotEqualTo(LottoNumbersGenerator.getLottoNumbers());
    }

    @Test
    void 입력한_번호를_제대로_생성하는지_확인() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);

        LottoNumbers lottoNumbers = new LottoNumbers(Arrays.asList(
                LottoNumber.valueOf(1),
                LottoNumber.valueOf(2),
                LottoNumber.valueOf(3),
                LottoNumber.valueOf(4),
                LottoNumber.valueOf(5),
                LottoNumber.valueOf(6)));

        assertThat(LottoNumbersGenerator.getLottoNumbers(numbers)).isEqualTo(lottoNumbers);
    }

    @Test
    void 입력한_번호가_1미만인_경우() {
        assertThrows(OutOfLottoNumberBoundException.class, () ->
                LottoNumbersGenerator.getLottoNumbers(Arrays.asList(0, 2, 3, 4, 5, 45)));
    }

    @Test
    void 입력한_번호가_45초과인_경우() {
        assertThrows(OutOfLottoNumberBoundException.class, () ->
                LottoNumbersGenerator.getLottoNumbers(Arrays.asList(1, 2, 3, 4, 5, 46)));
    }
}
