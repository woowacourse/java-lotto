package domain;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import argumentconverter.LottoNumbersConverter;
import argumentconverter.WinningLottoConverter;
import java.util.List;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.CsvSource;

class LottoTest {

    @ParameterizedTest
    @CsvSource(value = {
        "1,2,3,4,5,6",
        "40,41,42,43,44,45"
    }, delimiterString = "|")
    void 제대로된_번호가_오면_예외가_발생하지_않는다(
        @ConvertWith(LottoNumbersConverter.class) List<LottoNumber> numbers) {
        assertDoesNotThrow(() -> new Lotto(numbers));
    }

    @ParameterizedTest
    @CsvSource(value = {
        "1,2,3,4,5",
        "1,2,3,4,5,6,7"
    }, delimiterString = "|")
    void 번호가_6개가_아니면_예외가_발생한다(@ConvertWith(LottoNumbersConverter.class) List<LottoNumber> numbers) {
        assertThrows(IllegalArgumentException.class, () -> new Lotto(numbers));
    }

    @ParameterizedTest
    @CsvSource(value = {
        "1,2,3,4,5,5",
        "41,42,43,44,45,45"
    })
    void 번호가_중복되면_예외가_발생한다(@ConvertWith(LottoNumbersConverter.class) List<LottoNumber> numbers) {
        assertThrows(IllegalArgumentException.class, () -> new Lotto(numbers));
    }

    @ParameterizedTest
    @CsvSource(value = {
        "1,2,3,4,5,6|1,2,3,4,5,6,45|FIRST",
        "1,2,3,4,5,6|1,2,3,4,5,45,6|SECOND",
        "1,2,3,4,5,6|1,2,3,4,5,45,44|THIRD",
        "1,2,3,4,5,6|1,2,3,4,43,44,6|FOURTH",
        "1,2,3,4,5,6|1,2,3,42,43,44,6|FIFTH",
        "1,2,3,4,5,6|1,2,41,42,43,44,6|NONE"
    }, delimiterString = "|")
    void 당첨로또와_비교해_등수를_매긴다(
        @ConvertWith(LottoNumbersConverter.class) List<LottoNumber> lottoNumbers,
        @ConvertWith(WinningLottoConverter.class) WinningLotto winningLotto,
        PrizeTier expectedPrizeTier) {
        Lotto lotto = new Lotto(lottoNumbers);

        lotto.rankTier(winningLotto);
        PrizeTier actualPrizeTier = lotto.getPrizeTier();

        assertEquals(expectedPrizeTier, actualPrizeTier);
    }
}
