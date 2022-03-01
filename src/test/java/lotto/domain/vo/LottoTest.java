package lotto.domain.vo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.Assertions.assertThatNoException;

import java.util.List;
import lotto.domain.LottoPrize;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoTest {

    @DisplayName("로또 번호를 가진 Lotto 객체를 생성한다")
    @Test
    void lotto_constructor_test() {
        List<LottoNumber> lottoNumbers = List.of(
                LottoNumber.valueOf(1), LottoNumber.valueOf(2), LottoNumber.valueOf(3),
                LottoNumber.valueOf(4), LottoNumber.valueOf(5), LottoNumber.valueOf(6));
        assertThatNoException().isThrownBy(() -> new Lotto(lottoNumbers));
    }

    @DisplayName("null 입력 시 예외가 발생한다")
    @Test
    void lotto_constructor_error_null_test() {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> new Lotto(null))
                .withMessage("로또 숫자가 없습니다.");
    }

    @DisplayName("6개가 아닌 숫자를 입력 시 예외가 발생한다")
    @Test
    void lotto_constructor_error_not_six_test() {
        List<LottoNumber> lottoNumbers = List.of(
                LottoNumber.valueOf(1), LottoNumber.valueOf(2), LottoNumber.valueOf(3),
                LottoNumber.valueOf(4), LottoNumber.valueOf(5), LottoNumber.valueOf(6), LottoNumber.valueOf(7));

        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> new Lotto(lottoNumbers))
                .withMessage("로또 숫자는 6개여야 합니다.");
    }

    @DisplayName("로또 번호가 중복되면 예외가 발생한다")
    @Test
    void lotto_constructor_error_on_duplication_test() {
        List<LottoNumber> lottoNumbers = List.of(
                LottoNumber.valueOf(1), LottoNumber.valueOf(2), LottoNumber.valueOf(3),
                LottoNumber.valueOf(4), LottoNumber.valueOf(10), LottoNumber.valueOf(10));

        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> new Lotto(lottoNumbers))
                .withMessage("로또 숫자는 중복되면 안됩니다.");
    }

    @DisplayName("confirmWinning 당첨 등수를 반환한다")
    @Test
    void confirmWinning_test() {
        List<LottoNumber> buyNumber = List.of(
                LottoNumber.valueOf(1), LottoNumber.valueOf(2), LottoNumber.valueOf(3),
                LottoNumber.valueOf(4), LottoNumber.valueOf(5), LottoNumber.valueOf(45));
        List<LottoNumber> winningNumber = List.of(
                LottoNumber.valueOf(1), LottoNumber.valueOf(2), LottoNumber.valueOf(3),
                LottoNumber.valueOf(4), LottoNumber.valueOf(5), LottoNumber.valueOf(6));
        LottoNumber bonusNumber = LottoNumber.valueOf(45);

        WinningNumbers winningNumbers = new WinningNumbers(new Lotto(winningNumber), bonusNumber);
        Lotto lotto = new Lotto(buyNumber);
        LottoPrize prize = lotto.confirmWinning(winningNumbers);

        assertThat(prize).isEqualTo(LottoPrize.TWICE);
    }
}
