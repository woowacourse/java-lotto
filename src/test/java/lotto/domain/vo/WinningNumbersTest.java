package lotto.domain.vo;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.Assertions.assertThatNoException;

import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class WinningNumbersTest {

    @DisplayName("당첨 번호와 보너스 번호를 가진 객체를 생성한다")
    @Test
    void constructor_test() {
        List<LottoNumber> lottoNumbers = List.of(1, 2, 3, 4, 5, 6).stream()
                .map(LottoNumber::valueOf)
                .collect(Collectors.toList());
        LottoNumber bonusNumber = LottoNumber.valueOf(30);

        assertThatNoException()
                .isThrownBy(() -> new WinningNumbers(new Lotto(lottoNumbers), bonusNumber));
    }

    @DisplayName("6개가 아닌 당첨 번호가 입력되면 예외가 발생한다")
    @Test
    void constructor_error_winning_number_not_six_test() {
        List<LottoNumber> lottoNumbers = List.of(1, 2, 3, 4, 5, 6, 7).stream()
                .map(LottoNumber::valueOf)
                .collect(Collectors.toList());
        LottoNumber bonusNumber = LottoNumber.valueOf(30);

        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> new WinningNumbers(new Lotto(lottoNumbers), bonusNumber))
                .withMessage("로또 숫자는 6개여야 합니다.");
    }

    @DisplayName("당첨 번호가 중복되면 예외가 발생한다")
    @Test
    void constructor_error_on_winning_number_duplication_test() {
        List<LottoNumber> lottoNumbers = List.of(1, 1, 2, 3, 4, 5).stream()
                .map(LottoNumber::valueOf)
                .collect(Collectors.toList());
        LottoNumber bonusNumber = LottoNumber.valueOf(30);

        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> new WinningNumbers(new Lotto(lottoNumbers), bonusNumber))
                .withMessage("로또 숫자는 중복되면 안됩니다.");
    }

    @DisplayName("당첨 번호와 보너스 번호가 중복되면 예외가 발생한다")
    @Test
    void constructor_error_on_bonus_number_duplication_test() {
        List<LottoNumber> lottoNumbers = List.of(1, 2, 3, 4, 5, 6).stream()
                .map(LottoNumber::valueOf)
                .collect(Collectors.toList());
        LottoNumber bonusNumber = LottoNumber.valueOf(1);

        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> new WinningNumbers(new Lotto(lottoNumbers), bonusNumber))
                .withMessage("보너스 숫자는 로또 숫자와 중복되면 안됩니다.");
    }
}
