package model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static util.LottoUtil.*;

import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;

class LottoTest {

    @Test
    void 로또의_번호가_6개가_아닌_경우_예외가_발생한다() {
        assertThatThrownBy(() -> generateTestLotto(List.of(1, 2, 3, 4, 5, 6, 7)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 보너스_번호가_포함되지_않은_경우_로또의_등수를_계산한다() {
        WinningLotto winningLotto = new WinningLotto(
                generateTestLotto(List.of(1, 2, 3, 4, 5, 6)),
                new LottoNumber(19)
        );
        Lotto lotto = generateTestLotto(List.of(7, 8, 3, 4, 5, 6));
        LottoEvaluator lottoEvaluator = new LottoEvaluator(winningLotto);
        Prize prize = lottoEvaluator.calculatePrize(lotto);
        assertThat(prize).isEqualTo(Prize._4TH);
    }

    @Test
    void 보너스_번호가_포함된_경우_로또의_등수를_계산한다() {
        WinningLotto winningLotto = new WinningLotto(
                generateTestLotto(List.of(1, 8, 3, 4, 5, 6)),
                new LottoNumber(7)
        );
        Lotto lotto = generateTestLotto(List.of(7, 8, 3, 4, 5, 6));
        LottoEvaluator lottoEvaluator = new LottoEvaluator(winningLotto);
        Prize prize = lottoEvaluator.calculatePrize(lotto);
        assertThat(prize).isEqualTo(Prize._2ND);
    }
}
