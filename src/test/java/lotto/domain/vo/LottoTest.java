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

    @DisplayName("Lotto 생성자 테스트")
    @Test
    void lotto_constructor_test() {
        assertThatNoException().isThrownBy(() -> new Lotto(lottoNumbers));
    }

    @DisplayName("Lotto 생성자 6개가 아닌 숫자 입력 예외 테스트")
    @Test
    void lotto_constructor_error_not_six_test() {
        lottoNumbers.add(new LottoNumber(7));

        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> new Lotto(lottoNumbers))
                .withMessage("로또 숫자는 6개여야 합니다.");
    }

    @DisplayName("Lotto 생성자 숫자 중복 예외 테스트")
    @Test
    void lotto_constructor_error_on_duplication_test() {
        lottoNumbers.set(0, new LottoNumber(45));
        lottoNumbers.set(1, new LottoNumber(45));

        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> new Lotto(lottoNumbers))
                .withMessage("로또 숫자는 중복되면 안됩니다.");
    }

    @DisplayName("confirmWinning 메소드 테스트")
    @Test
    void confirmWinning_test() {
        List<LottoNumber> lottoNumbers = new ArrayList<>(this.lottoNumbers);
        lottoNumbers.set(0, new LottoNumber(45));
        WinningNumbers winningNumbers = new WinningNumbers(this.lottoNumbers, new LottoNumber(45));
        Lotto lotto = new Lotto(lottoNumbers);
        LottoPrize prize = lotto.confirmWinning(winningNumbers);

        assertThat(prize).isEqualTo(LottoPrize.TWICE);
    }
}
