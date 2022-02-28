package lotto.domain.vo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.Assertions.assertThatNoException;

import java.util.ArrayList;
import java.util.List;
import lotto.domain.LottoPrize;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoTest {

    private List<LottoNumber> lottoNumbers;

    @BeforeEach
    void setup() {
        lottoNumbers = new ArrayList<>();
        for (int i = 1; i <= 6; i++) {
            lottoNumbers.add(new LottoNumber(i));
        }
    }

    @DisplayName("로또 번호를 가진 Lotto 객체를 생성한다")
    @Test
    void lotto_constructor_test() {
        assertThatNoException().isThrownBy(() -> new Lotto(lottoNumbers));
    }

    @DisplayName("null 입력 시 IllegalArgumentException 예외가 발생한다")
    @Test
    void lotto_constructor_error_null_test() {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> new Lotto(null))
                .withMessage("로또 숫자가 없습니다.");
    }

    @DisplayName("6개가 아닌 숫자를 입력 시 IllegalArgumentException 예외가 발생한다")
    @Test
    void lotto_constructor_error_not_six_test() {
        lottoNumbers.add(new LottoNumber(7));

        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> new Lotto(lottoNumbers))
                .withMessage("로또 숫자는 6개여야 합니다.");
    }

    @DisplayName("로또 번호가 중복되면 IllegalArgumentException 예외가 발생한다")
    @Test
    void lotto_constructor_error_on_duplication_test() {
        lottoNumbers.set(0, new LottoNumber(45));
        lottoNumbers.set(1, new LottoNumber(45));

        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> new Lotto(lottoNumbers))
                .withMessage("로또 숫자는 중복되면 안됩니다.");
    }

    @DisplayName("confirmWinning 당첨 등수를 반환한다")
    @Test
    void confirmWinning_test() {
        List<LottoNumber> lottoNumbers = new ArrayList<>(this.lottoNumbers);
        lottoNumbers.set(0, new LottoNumber(45));
        WinningNumbers winningNumbers = new WinningNumbers(new Lotto(this.lottoNumbers), new LottoNumber(45));
        Lotto lotto = new Lotto(lottoNumbers);
        LottoPrize prize = lotto.confirmWinning(winningNumbers);

        assertThat(prize).isEqualTo(LottoPrize.TWICE);
    }
}
