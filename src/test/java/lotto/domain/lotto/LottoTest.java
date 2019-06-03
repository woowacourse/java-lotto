package lotto.domain.lotto;

import lotto.domain.InvalidLottoException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LottoTest {

    private Lotto lotto;
    private List<LottoNumber> lottoNumbers;
    private List<LottoNumber> winningLottoNumbers;

    @BeforeEach
    void setUp() {
        lottoNumbers = new ArrayList<>();
        winningLottoNumbers = new ArrayList<>();
    }

    @Test
    void Lotto_번호_6개_잘들어가는지_테스트() {
        for (int i = 1; i < 7; i++) {
            lottoNumbers.add(new LottoNumber(i));
        }
        lotto = new Lotto(lottoNumbers);
        assertThat(lotto).isEqualTo(new Lotto(lottoNumbers));
    }

    @Test
    void Lotto_번호가_6개_미만일때() {
        for (int i = 1; i < 6; i++) {
            lottoNumbers.add(new LottoNumber(i));
        }
        assertThrows(InvalidLottoException.class, () -> {
            new Lotto(lottoNumbers);
        });
    }

    @Test
    void Lotto_번호가_6개_이상일때() {
        for (int i = 1; i < 8; i++) {
            lottoNumbers.add(new LottoNumber(i));
        }
        assertThrows(InvalidLottoException.class, () -> {
            new Lotto(lottoNumbers);
        });
    }

    @Test
    void 매칭_시스템에서_개수가_잘나오는지() {
        for (int i = 1; i < 7; i++) {
            lottoNumbers.add(new LottoNumber(i));
        }
        for (int i = 1; i < 7; i++) {
            winningLottoNumbers.add(new LottoNumber(i));
        }

        assertThat(new Lotto(lottoNumbers)
                .numberOfMatch(new Lotto(winningLottoNumbers)))
                .isEqualTo(6);
    }

    @Test
    void 매칭_시스템에서_보너스볼의_값이_잘나오는지() {
        for (int i = 1; i < 7; i++) {
            lottoNumbers.add(new LottoNumber(i));
        }

        for (int i = 2; i < 8; i++) {
            winningLottoNumbers.add(new LottoNumber(i));
        }

        assertTrue(new Lotto(lottoNumbers)
                .bonusOfMatch(new BonusBall(
                        new Lotto(winningLottoNumbers), "1")));
    }

    @AfterEach
    void tearDown() {
        lottoNumbers = null;
    }
}
