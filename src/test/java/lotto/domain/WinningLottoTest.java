package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class WinningLottoTest {

    @Test
    @DisplayName("몇 등에 당첨인지 확인")
    void winningLotto_findRank() {
        Lotto winLotto = new Lotto(Arrays.asList(
            new LottoNumber(1),
            new LottoNumber(2),
            new LottoNumber(3),
            new LottoNumber(4),
            new LottoNumber(5),
            new LottoNumber(6)
        ));
        WinningLotto winningLotto = new WinningLotto(winLotto, new LottoNumber(7));

        Lotto lotto = new Lotto(Arrays.asList(
            new LottoNumber(1),
            new LottoNumber(2),
            new LottoNumber(3),
            new LottoNumber(4),
            new LottoNumber(5),
            new LottoNumber(7)
        ));
        Rank rank = winningLotto.findRank(lotto);
        assertThat(rank).isEqualTo(Rank.SECOND);
    }
}
