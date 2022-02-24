package lotto.domain;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.Assertions.assertThatNoException;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class WinningNumbersTest {

    List<LottoNumber> lottoNumbers;
    LottoNumber bonusNumber;

    @BeforeEach
    void setup() {
        lottoNumbers = new ArrayList<>();
        for (int i = 1; i <= 6; i++) {
            lottoNumbers.add(new LottoNumber(i));
        }

        bonusNumber = new LottoNumber(30);
    }

    @DisplayName("WinningNumbers 생성자는 당첨 번호와 보너스 번호를 입력받아 값을 초기화한다.")
    @Test
    void constructor() {
        assertThatNoException()
                .isThrownBy(() -> new WinningNumbers(lottoNumbers, bonusNumber));
    }

    @DisplayName("WinningNumbers 생성자는 6개가 아닌 숫자를 입력하면 예외가 발생한다.")
    @Test
    void constructor_errorWinningNumberNotSix() {
        lottoNumbers.add(new LottoNumber(7));

        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> new WinningNumbers(lottoNumbers, bonusNumber))
                .withMessage("로또 숫자는 6개여야 합니다.");
    }

    @DisplayName("WinningNumbers 생성자는 입력된 당첨 번호가 중복될 경우 예외가 발생한다.")
    @Test
    void constructor_errorOnWinningNumberDuplication() {
        lottoNumbers.set(0, new LottoNumber(2));

        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> new WinningNumbers(lottoNumbers, bonusNumber))
                .withMessage("로또 숫자는 중복되면 안됩니다.");
    }

    @DisplayName("WinningNumbers 생성자는 보너스 번호가 당첨 번호와 중복될 경우 예외가 발생한다.")
    @Test
    void constructor_errorOnBonusNumberDuplication() {
        bonusNumber = new LottoNumber(1);

        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> new WinningNumbers(lottoNumbers, bonusNumber))
                .withMessage("보너스 숫자는 로또 숫자와 중복되면 안됩니다.");
    }
}
