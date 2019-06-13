package lotto.domain.lotto;

import lotto.domain.BonusBall;
import lotto.domain.CustomGenerateLotto;
import lotto.domain.Lotto;
import lotto.domain.WinningLotto;
import lotto.domain.exception.InvalidCustomGenerateLottoException;
import lotto.domain.exception.InvalidLottoException;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CustomGenerateLottoTest {
    private String inputLottoNumbers;
    private Lotto lotto;

    @Test
    void Lotto_번호_6개_잘들어가는지_테스트() {
        this.inputLottoNumbers = "1,2,3,4,5,6";
        lotto = new CustomGenerateLotto(inputLottoNumbers);
        assertThat(lotto).isEqualTo(new CustomGenerateLotto(inputLottoNumbers));
    }

    @Test
    void Lotto_번호가_6개_미만일때() {
        this.inputLottoNumbers = "1,2,3,4,5";
        assertThrows(InvalidLottoException.class, () -> {
            new CustomGenerateLotto(inputLottoNumbers);
        });
    }

    @Test
    void Lotto_번호가_6개_이상일때() {
        this.inputLottoNumbers = "1,2,3,4,5,6,7";
        assertThrows(InvalidLottoException.class, () -> {
            new CustomGenerateLotto(inputLottoNumbers);
        });
    }

    @Test
    void Lotto_번호에_실수를_입력할때() {
        this.inputLottoNumbers = "1.1,2,3,4,5,6";
        assertThrows(InvalidCustomGenerateLottoException.class, () -> {
            new CustomGenerateLotto(inputLottoNumbers);
        });
    }

    @Test
    void Lotto_번호에_문자를_입력할때() {
        this.inputLottoNumbers = "a,2,3,4,5,6";
        assertThrows(InvalidCustomGenerateLottoException.class, () -> {
            new CustomGenerateLotto(inputLottoNumbers);
        });
    }

    @Test
    void 매칭_시스템에서_개수가_잘나오는지() {
        this.inputLottoNumbers = "1,2,3,4,5,6";
        String winningLottoNumbers = "1,2,3,4,5,6";

        assertThat(new CustomGenerateLotto(inputLottoNumbers)
                .numberOfMatch(new WinningLotto(winningLottoNumbers, "7").getWinningNumbers()))
                .isEqualTo(6);
    }

    @Test
    void 매칭_시스템에서_보너스볼의_값이_잘나오는지() {
        this.inputLottoNumbers = "1,2,3,4,5,6";
        String winningLottoNumbers = "2,3,4,5,6,7";

        assertTrue(new CustomGenerateLotto(inputLottoNumbers)
                .bonusOfMatch(new BonusBall(
                        new WinningLotto(winningLottoNumbers, "1").getWinningNumbers(), "1")));
    }
}
