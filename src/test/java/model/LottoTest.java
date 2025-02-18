package model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static util.LottoUtil.generateTestLotto;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class LottoTest {

    @Test
    void 로또의_번호가_6개가_아니면_오류가_발생한다() {
        assertThatThrownBy(() -> generateTestLotto(1, 2, 3, 4, 5, 6, 7))
                .isInstanceOf(RuntimeException.class);
    }

    @Test
    void 로또의_등수를_계산한다() {
        Lotto answer = generateTestLotto(1, 2, 3, 4, 5, 6);
        Lotto lotto = generateTestLotto(7, 8, 3, 4, 5, 6);

        WinningLotto winningLotto = new WinningLotto(answer, new LottoNumber(19));
        LottoEvaluator lottoEvaluator = new LottoEvaluator(winningLotto);
        Prize prize = lottoEvaluator.calculatePrize(lotto).get();
        assertThat(prize).isEqualTo(Prize._4TH);
    }

    @Test
    void 보너스_번호가_포함된_로또의_등수를_계산한다() {
        Lotto answer = generateTestLotto(1, 8, 3, 4, 5, 6);
        Lotto lotto = generateTestLotto(7, 8, 3, 4, 5, 6);

        WinningLotto winningLotto = new WinningLotto(answer, new LottoNumber(7));
        LottoEvaluator lottoEvaluator = new LottoEvaluator(winningLotto);
        Prize prize = lottoEvaluator.calculatePrize(lotto).get();
        assertThat(prize).isEqualTo(Prize._2ND);
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5, 6})
    void 로또가_특정_숫자를_포함하는지_확인한다(int number) {
        Lotto lotto = generateTestLotto(1, 2, 3, 4, 5, 6);

        assertThat(lotto.containsNumber(new LottoNumber(number))).isEqualTo(true);
    }
}
