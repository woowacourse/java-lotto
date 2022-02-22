package lotto.model;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class WinningLottoTest {

    @Test
    void 로또_3등_당첨테스트() {
        Lotto lotto = new Lotto(Arrays.asList(1,2,3,4,6,8));
        WinningLotto winningLotto = new WinningLotto(new Lotto(Arrays.asList(1,2,3,4,5,6)), 7);
        assertThat(winningLotto.calculateRank(lotto)).isEqualTo(Rank.THIRD);
    }

    @Test
    void 로또_2등_당첨테스트() {
        Lotto lotto = new Lotto(Arrays.asList(1,2,3,4,6,7));
        WinningLotto winningLotto = new WinningLotto(new Lotto(Arrays.asList(1,2,3,4,5,6)), 7);
        assertThat(winningLotto.calculateRank(lotto)).isEqualTo(Rank.SECOND);
    }


}
