package model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static util.LottoUtil.*;

import java.util.Optional;
import org.junit.jupiter.api.Test;

class LottoTest {

    @Test
    void 로또의_로또_번호는_6개이다() {
        assertThatThrownBy(() -> generateTestLotto(1, 2, 3, 4, 5, 6, 7))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 로또의_등수를_계산한다() {
        WinningLotto winningLotto = new WinningLotto(
                generateTestLotto(1, 2, 3, 4, 5, 6),
                new LottoNumber(19)
        );
        Lotto lotto = generateTestLotto(7, 8, 3, 4, 5, 6);
        LottoEvaluator lottoEvaluator = new LottoEvaluator(winningLotto);
        Optional<Prize> prize = lottoEvaluator.calculatePrize(lotto);
        assertThat(prize).contains(Prize._4TH);
    }

    @Test
    void 보너스_번호가_포함된_로또의_등수를_계산한다() {
        WinningLotto winningLotto = new WinningLotto(
                generateTestLotto(1, 8, 3, 4, 5, 6),
                new LottoNumber(7)
        );
        Lotto lotto = generateTestLotto(7, 8, 3, 4, 5, 6);
        LottoEvaluator lottoEvaluator = new LottoEvaluator(winningLotto);
        Optional<Prize> prize = lottoEvaluator.calculatePrize(lotto);
        assertThat(prize).contains(Prize._2ND);
    }
}
