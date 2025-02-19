package domain;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import argumentconverter.LottoNumbersConverter;
import java.util.List;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

@DisplayNameGeneration(ReplaceUnderscores.class)
class WinningLottoTest {

    @ParameterizedTest
    @CsvSource(value = {"1,2,3,4,5,6|7", "39,40,41,42,43,44|45"}, delimiterString = "|")
    void 정상적인_보너스번호로_당첨로또를_생성하면_예외를_발생시키지않는다(
        @ConvertWith(LottoNumbersConverter.class) List<LottoNumber> lottoNumbers, int bonusNumber) {
        Lotto basicLotto = new Lotto(lottoNumbers);
        LottoNumber bonusLottoNumber = new LottoNumber(bonusNumber);

        assertDoesNotThrow(() -> new WinningLotto(basicLotto, bonusLottoNumber));
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5, 6})
    void 보너스번호가_중복되면_예외를_발생시킨다(int bonusNumber) {
        Lotto basicLotto = new Lotto(List.of(
            new LottoNumber(1), new LottoNumber(2), new LottoNumber(3),
            new LottoNumber(4), new LottoNumber(5), new LottoNumber(6))
        );
        LottoNumber duplicatedBonusLottoNumber = new LottoNumber(bonusNumber);

        assertThrows(IllegalArgumentException.class,
            () -> new WinningLotto(basicLotto, duplicatedBonusLottoNumber));
    }
}
