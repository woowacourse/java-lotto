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
            lottoNumbers.add(new LottoNumber(i));
        }

        bonusNumber = new LottoNumber(30);
    }

    @DisplayName("WinningNumbers 생성자 테스트")
    @Test
    void constructor_test() {
        assertThatNoException()
                .isThrownBy(() -> new WinningNumbers(new Lotto(lottoNumbers), bonusNumber));
    }

    @DisplayName("WinningNumbers 생성자 6개가 아닌 숫자 입력 예외 테스트")
    @Test
    void constructor_error_winning_number_not_six_test() {
        lottoNumbers.add(new LottoNumber(7));

        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> new WinningNumbers(new Lotto(lottoNumbers), bonusNumber))
                .withMessage("로또 숫자는 6개여야 합니다.");
    }

    @DisplayName("WinningNumbers 당첨 번호 중복 예외 테스트")
    @Test
    void constructor_error_on_winning_number_duplication_test() {
        lottoNumbers.set(0, new LottoNumber(45));
        lottoNumbers.set(1, new LottoNumber(45));

        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> new WinningNumbers(new Lotto(lottoNumbers), bonusNumber))
                .withMessage("로또 숫자는 중복되면 안됩니다.");
    }

    @DisplayName("WinningNumbers 보너스 번호 중복 예외 테스트")
    @Test
    void constructor_error_on_bonus_number_duplication_test() {
        bonusNumber = new LottoNumber(lottoNumbers.get(0).get());

        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> new WinningNumbers(new Lotto(lottoNumbers), bonusNumber))
                .withMessage("보너스 숫자는 로또 숫자와 중복되면 안됩니다.");
    }
}
