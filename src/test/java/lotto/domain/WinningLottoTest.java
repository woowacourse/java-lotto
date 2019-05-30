package lotto.domain;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static lotto.domain.Rank.*;

public class WinningLottoTest {

    @Test
    void 로또_등수_계산_보너스_번호_추가_1등() {
        Lotto lotto = new Lotto(Arrays.asList(1, 2, 3, 4, 5, 6));
        LottoNumber bonus = LottoNumber.get(7);
        WinningLotto winningLotto = new WinningLotto(lotto, bonus);
        assertThat(FIRST).isEqualTo(winningLotto.getWinning(lotto));
    }

    @Test
    void 로또_등수_계산_보너스_번호_추가_2등() {
        Lotto currentLotto = new Lotto(Arrays.asList(1, 2, 3, 4, 5, 10));
        Lotto beforeWinningLotto = new Lotto(Arrays.asList(1, 2, 3, 4, 5, 9));
        LottoNumber bonus = LottoNumber.get(10);
        WinningLotto winningLotto = new WinningLotto(beforeWinningLotto, bonus);
        assertThat(SECOND).isEqualTo(winningLotto.getWinning(currentLotto));
    }

    @Test
    void 로또_등수_계산_보너스_번호_추가_3등() {
        Lotto currentLotto = new Lotto(Arrays.asList(1, 2, 3, 4, 5, 10));
        Lotto beforeWinningLotto = new Lotto(Arrays.asList(1, 2, 3, 4, 5, 9));
        LottoNumber bonus = LottoNumber.get(7);
        WinningLotto winningLotto = new WinningLotto(beforeWinningLotto, bonus);
        assertThat(THIRD).isEqualTo(winningLotto.getWinning(currentLotto));
    }
}
