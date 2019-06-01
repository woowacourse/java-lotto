package lotto.domain;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static lotto.domain.Rank.*;

public class WinningLottoTest {

    @Test
    void 로또_등수_계산_보너스_번호_추가_1등() {
        Lotto lotto = new Lotto(Arrays.asList(
                LottoNumber.getInstance(1),
                LottoNumber.getInstance(2),
                LottoNumber.getInstance(3),
                LottoNumber.getInstance(4),
                LottoNumber.getInstance(5),
                LottoNumber.getInstance(6)
        ));
        LottoNumber bonus = LottoNumber.getInstance(7);
        WinningLotto winningLotto = new WinningLotto(lotto, bonus);
        assertThat(FIRST).isEqualTo(winningLotto.getWinning(lotto));
    }

    @Test
    void 로또_등수_계산_보너스_번호_추가_2등() {
        Lotto currentLotto = new Lotto(Arrays.asList(
                LottoNumber.getInstance(1),
                LottoNumber.getInstance(2),
                LottoNumber.getInstance(3),
                LottoNumber.getInstance(4),
                LottoNumber.getInstance(5),
                LottoNumber.getInstance(10)
        ));
        Lotto beforeWinningLotto = new Lotto(Arrays.asList(
                LottoNumber.getInstance(1),
                LottoNumber.getInstance(2),
                LottoNumber.getInstance(3),
                LottoNumber.getInstance(4),
                LottoNumber.getInstance(5),
                LottoNumber.getInstance(9)
        ));
        LottoNumber bonus = LottoNumber.getInstance(10);
        WinningLotto winningLotto = new WinningLotto(beforeWinningLotto, bonus);
        assertThat(SECOND).isEqualTo(winningLotto.getWinning(currentLotto));
    }

    @Test
    void 로또_등수_계산_보너스_번호_추가_3등() {
        Lotto currentLotto = new Lotto(Arrays.asList(
                LottoNumber.getInstance(1),
                LottoNumber.getInstance(2),
                LottoNumber.getInstance(3),
                LottoNumber.getInstance(4),
                LottoNumber.getInstance(5),
                LottoNumber.getInstance(10)
        ));
        Lotto beforeWinningLotto = new Lotto(Arrays.asList(
                LottoNumber.getInstance(1),
                LottoNumber.getInstance(2),
                LottoNumber.getInstance(3),
                LottoNumber.getInstance(4),
                LottoNumber.getInstance(5),
                LottoNumber.getInstance(9)
        ));
        LottoNumber bonus = LottoNumber.getInstance(7);
        WinningLotto winningLotto = new WinningLotto(beforeWinningLotto, bonus);
        assertThat(THIRD).isEqualTo(winningLotto.getWinning(currentLotto));
    }
}
