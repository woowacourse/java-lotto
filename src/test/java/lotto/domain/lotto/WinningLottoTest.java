package lotto.domain.lotto;

import lotto.domain.Rank;
import lotto.domain.lottogenerator.LottoGenerator;
import lotto.domain.lottogenerator.ManualLottoGeneratingStrategy;
import lotto.exception.DuplicateLottoNumberException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

class WinningLottoTest {
    private Lotto lotto;

    @BeforeEach
    void setUp() {
        lotto = LottoGenerator.create(new ManualLottoGeneratingStrategy(Arrays.asList(1, 2, 3, 4, 5, 6)));
    }

    @Test
    void 생성자_확인() {
        assertThat(new WinningLotto(lotto, LottoNumber.getNumber(7)))
                .isExactlyInstanceOf((WinningLotto.class));
    }

    @Test
    void 생성자_확인_당첨번호_로또가_null인_경우() {
        assertThatThrownBy(() -> new WinningLotto(null, LottoNumber.getNumber(7)))
                .isInstanceOf(NullPointerException.class);
    }

    @Test
    void 생성자_확인_보너스번호가_null인_경우() {
        assertThatThrownBy(() -> new WinningLotto(lotto, null))
                .isInstanceOf(NullPointerException.class);
    }

    @Test
    void 생성자_확인_보너스_숫자와_이미_입력된_로또숫자가_중복되는지_확인() {
        assertThatThrownBy(() -> new WinningLotto(lotto, LottoNumber.getNumber(1)))
                .isExactlyInstanceOf(DuplicateLottoNumberException.class);
    }

    @Test
    void 로또번호와_당첨번호가_일치하는지_확인() {
        WinningLotto winningLotto = new WinningLotto(lotto, LottoNumber.getNumber(7));
        Lotto lottoToCompare = LottoGenerator.create(new ManualLottoGeneratingStrategy(Arrays.asList(1, 2, 3, 4, 5, 6)));

        assertThat(winningLotto.match(lottoToCompare)).isEqualTo(Rank.FIRST);
    }
}