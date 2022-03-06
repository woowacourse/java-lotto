package lotto.domain.vo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.Assertions.assertThatNoException;

import java.util.List;
import java.util.stream.Collectors;
import lotto.domain.LottoPrize;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoTest {

    @DisplayName("로또 번호를 가진 Lotto 객체를 생성한다")
    @Test
    void lotto_constructor_test() {
        List<LottoNumber> lottoNumbers = List.of(1, 2, 3, 4, 5, 6).stream()
                .map(LottoNumber::valueOf)
                .collect(Collectors.toList());

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
        List<LottoNumber> lottoNumbers = List.of(1, 2, 3, 4, 5, 6, 7).stream()
                .map(LottoNumber::valueOf)
                .collect(Collectors.toList());

        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> new Lotto(lottoNumbers))
                .withMessage("로또 숫자는 6개여야 합니다.");
    }

    @DisplayName("로또 번호가 중복되면 예외가 발생한다")
    @Test
    void lotto_constructor_error_on_duplication_test() {
        List<LottoNumber> lottoNumbers = List.of(1, 1, 2, 3, 4, 5).stream()
                .map(LottoNumber::valueOf)
                .collect(Collectors.toList());

        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> new Lotto(lottoNumbers))
                .withMessage("로또 숫자는 중복되면 안됩니다.");
    }

    @DisplayName("confirmWinning 당첨 등수를 반환한다")
    @Test
    void confirmWinning_test() {
        // given
        List<LottoNumber> buyNumber = List.of(1, 2, 3, 4, 5, 45).stream()
                .map(LottoNumber::valueOf)
                .collect(Collectors.toList());
        List<LottoNumber> winningNumber = List.of(1, 2, 3, 4, 5, 6).stream()
                .map(LottoNumber::valueOf)
                .collect(Collectors.toList());
        LottoNumber bonusNumber = LottoNumber.valueOf(45);

        WinningNumbers winningNumbers = new WinningNumbers(new Lotto(winningNumber), bonusNumber);
        Lotto lotto = new Lotto(buyNumber);

        // when
        LottoPrize prize = lotto.confirmWinning(winningNumbers);

        // then
        assertThat(prize).isEqualTo(LottoPrize.TWICE);
    }
}
