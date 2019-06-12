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
    private Lotto lotto;

    @Test
    void Lotto_번호_6개_잘들어가는지_테스트() {
        String[] lottoNumbers = {"1", "2", "3", "4", "5", "6"};
        lotto = new CustomGenerateLotto(lottoNumbers);
        assertThat(lotto).isEqualTo(new CustomGenerateLotto(lottoNumbers));
    }

    @Test
    void Lotto_번호가_6개_미만일때() {
        String[] lottoNumbers = {"1", "2", "3", "4", "5"};
        assertThrows(InvalidLottoException.class, () -> {
            new CustomGenerateLotto(lottoNumbers);
        });
    }

    @Test
    void Lotto_번호가_6개_이상일때() {
        String[] lottoNumbers = {"1", "2", "3", "4", "5", "6", "7"};
        assertThrows(InvalidLottoException.class, () -> {
            new CustomGenerateLotto(lottoNumbers);
        });
    }

    @Test
    void Lotto_번호에_실수를_입력할때() {
        String[] lottoNumbers = {"1.1", "2", "3", "4", "5", "6"};
        assertThrows(InvalidCustomGenerateLottoException.class, () -> {
            new CustomGenerateLotto(lottoNumbers);
        });
    }

    @Test
    void Lotto_번호에_문자를_입력할때() {
        String[] lottoNumbers = {"a", "2", "3", "4", "5", "6"};
        assertThrows(InvalidCustomGenerateLottoException.class, () -> {
            new CustomGenerateLotto(lottoNumbers);
        });
    }

    @Test
    void 매칭_시스템에서_개수가_잘나오는지() {
        String[] lottoNumbers = {"1", "2", "3", "4", "5", "6"};
        String[] winningLottoNumbers = {"1", "2", "3", "4", "5", "6"};

        assertThat(new CustomGenerateLotto(lottoNumbers)
                .numberOfMatch(new WinningLotto(winningLottoNumbers, "7").getWinningLotto()))
                .isEqualTo(6);
    }

    @Test
    void 매칭_시스템에서_보너스볼의_값이_잘나오는지() {
        String[] lottoNumbers = {"1", "2", "3", "4", "5", "6"};
        String[] winningLottoNumbers = {"2", "3", "4", "5", "6", "7"};

        assertTrue(new CustomGenerateLotto(lottoNumbers)
                .bonusOfMatch(new BonusBall(
                        new WinningLotto(winningLottoNumbers, "1").getWinningLotto(), "1")));
    }
}
