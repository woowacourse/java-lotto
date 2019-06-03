package lotto.domain;

import lotto.domain.generator.LottoNumbersGenerator;
import lotto.domain.generator.ManualLottoNumbersGenerator;
import lotto.exception.NotRegisteredNumbersException;
import lotto.exception.OutOfLottoNumberBoundException;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ManualLottoNumbersGeneratorTest {

    @Test
    void 입력한_번호를_제대로_생성하는지_확인() {
        LottoNumbers lottoNumbers = new LottoNumbers(Arrays.asList(
                LottoNumber.valueOf(1),
                LottoNumber.valueOf(2),
                LottoNumber.valueOf(3),
                LottoNumber.valueOf(4),
                LottoNumber.valueOf(5),
                LottoNumber.valueOf(6)));
        LottoNumbersGenerator manualLottoNumbersGenerator =
                ManualLottoNumbersGenerator.getInstance("1,2,3,4,5,6");

        assertThat(manualLottoNumbersGenerator.generate()).isEqualTo(lottoNumbers);
    }

    @Test
    void 입력한_번호가_1미만인_경우() {
        LottoNumbersGenerator manualLottoNumbersGenerator =
                ManualLottoNumbersGenerator.getInstance("0, 2, 3, 4, 5, 45");
        assertThrows(OutOfLottoNumberBoundException.class, () ->
                manualLottoNumbersGenerator.generate());
    }

    @Test
    void 입력한_번호가_45초과인_경우() {
        LottoNumbersGenerator manualLottoNumbersGenerator =
                ManualLottoNumbersGenerator.getInstance("1, 2, 3, 4, 5, 46");
        assertThrows(OutOfLottoNumberBoundException.class, () ->
                manualLottoNumbersGenerator.generate());
    }

    @Test
    void 입력한_번호가_숫자가_아닌_경우() {
        LottoNumbersGenerator manualLottoNumbersGenerator =
                ManualLottoNumbersGenerator.getInstance("a, 2, 3, 4, 5, 45");
        assertThrows(NumberFormatException.class, () ->
                manualLottoNumbersGenerator.generate());
    }

    @Test
    void 번호가_null인_경우() {
        assertThrows(NotRegisteredNumbersException.class, () ->
                ManualLottoNumbersGenerator.getInstance(null));
    }
}
