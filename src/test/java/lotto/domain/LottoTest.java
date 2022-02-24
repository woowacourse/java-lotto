package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.Assertions.assertThatNoException;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoTest {

    private List<LottoNumber> lottoNumbers;

    @BeforeEach
    void setup() {
        lottoNumbers = new ArrayList<>();
        for (int i = 1; i <= 6; i++) {
            lottoNumbers.add(new LottoNumber(i));
        }
    }

    @DisplayName("Lotto 생성자는 LottoNumber 리스트를 입력받아 초기화한다.")
    @Test
    void constructor() {
        assertThatNoException().isThrownBy(() -> new Lotto(lottoNumbers));
    }

    @DisplayName("Lotto 생성자는 인자로 6개가 아닌 숫자를 입력했을 때 예외가 발생한다.")
    @Test
    void constructor_errorNotSix() {
        lottoNumbers.add(new LottoNumber(7));

        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> new Lotto(lottoNumbers))
                .withMessage("로또 숫자는 6개여야 합니다.");
    }

    @DisplayName("Lotto 생성자는 인자로 중복된 숫자를 입력하면 예외를 발생한다.")
    @Test
    void constructor_errorOnDuplication() {
        lottoNumbers.set(0, new LottoNumber(2));

        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> new Lotto(lottoNumbers))
                .withMessage("로또 숫자는 중복되면 안됩니다.");
    }

    @DisplayName("confirmWinning 메서드는 WinnigNumbers를 입력받아 당첨을 확인한다.")
    @Test
    void confirmWinning() {
        WinningNumbers winningNumbers = new WinningNumbers(new Lotto(lottoNumbers), new LottoNumber(30));
        Lotto lotto = new Lotto(lottoNumbers);
        LottoPrize prize = lotto.confirmWinning(winningNumbers);

        assertThat(prize).isEqualTo(LottoPrize.FIRST);
    }
}
