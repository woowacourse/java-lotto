package lotto.domain.vo;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.Assertions.assertThatNoException;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class WinningNumbersTest {

    List<LottoNumber> lottoNumbers;
    LottoNumber bonusNumber;

    @BeforeEach
    void setup() {
        lottoNumbers = new ArrayList<>();
        for (int i = 1; i <= 6; i++) {
            lottoNumbers.add(LottoNumber.valueOf(i));
        }

        bonusNumber = LottoNumber.valueOf(30);
    }

    @DisplayName("당첨 번호와 보너스 번호를 가진 WinningNumbers 객체를 생성한다")
    @Test
    void constructor_test() {
        assertThatNoException()
                .isThrownBy(() -> new WinningNumbers(new Lotto(lottoNumbers), bonusNumber));
    }

    @DisplayName("6개가 아닌 당첨 번호가 입력되면 IllegalArgumentException 예외가 발생한다")
    @Test
    void constructor_error_winning_number_not_six_test() {
        lottoNumbers.add(LottoNumber.valueOf(7));

        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> new WinningNumbers(new Lotto(lottoNumbers), bonusNumber))
                .withMessage("로또 숫자는 6개여야 합니다.");
    }

    @DisplayName("당첨 번호가 중복되면 IllegalArgumentException 예외가 발생한다")
    @Test
    void constructor_error_on_winning_number_duplication_test() {
        lottoNumbers.set(0, LottoNumber.valueOf(45));
        lottoNumbers.set(1, LottoNumber.valueOf(45));

        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> new WinningNumbers(new Lotto(lottoNumbers), bonusNumber))
                .withMessage("로또 숫자는 중복되면 안됩니다.");
    }

    @DisplayName("당첨 번호와 보너스 번호가 중복되면 IllegalArgumentException 예외가 발생한다")
    @Test
    void constructor_error_on_bonus_number_duplication_test() {
        bonusNumber = LottoNumber.valueOf(lottoNumbers.get(0).get());

        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> new WinningNumbers(new Lotto(lottoNumbers), bonusNumber))
                .withMessage("보너스 숫자는 로또 숫자와 중복되면 안됩니다.");
    }
}
