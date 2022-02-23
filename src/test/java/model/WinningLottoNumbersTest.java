package model;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import exception.DuplicatedLottoNumbersException;
import model.LottoNumber;
import model.LottoNumbers;
import model.WinningLottoNumbers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class WinningLottoNumbersTest {

    @Test
    @DisplayName("당첨번호와 보너스 번호 정상적으로 생성")
    void createValidWinningLottoNumbers() {
        LottoNumbers lottoNumbers = LottoNumbers.withSixNumbers(1,2,3,4,5,6);
        LottoNumber bonusNumber = new LottoNumber(7);
        assertThatCode(() -> new WinningLottoNumbers(lottoNumbers, bonusNumber))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("당첨번호와 보너스 번호 중복 생성 예외 발생")
    void duplicatedWinningLottoNumbers() {
        LottoNumbers lottoNumbers = LottoNumbers.withSixNumbers(1,2,3,4,5,6);
        LottoNumber bonusNumber = new LottoNumber(1);
        assertThatThrownBy(() -> new WinningLottoNumbers(lottoNumbers, bonusNumber))
                .isInstanceOf(DuplicatedLottoNumbersException.class);
    }
}
