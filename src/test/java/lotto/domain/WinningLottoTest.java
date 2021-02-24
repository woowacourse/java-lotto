package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.HashSet;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class WinningLottoTest {

    @Test
    @DisplayName("몇 등에 당첨인지 확인")
    void winningLotto_findRank() {
        Lotto winLotto = new Lotto(new HashSet<>(Arrays.asList(
            LottoNumber.of(1),
            LottoNumber.of(2),
            LottoNumber.of(3),
            LottoNumber.of(4),
            LottoNumber.of(5),
            LottoNumber.of(6)
        )));
        WinningLotto winningLotto = new WinningLotto(winLotto, LottoNumber.of(7));

        Lotto lotto = new Lotto(new HashSet<>(Arrays.asList(
            LottoNumber.of(1),
            LottoNumber.of(2),
            LottoNumber.of(3),
            LottoNumber.of(4),
            LottoNumber.of(5),
            LottoNumber.of(7)
        )));
        Rank rank = winningLotto.findRank(lotto);
        assertThat(rank).isEqualTo(Rank.SECOND);
    }
}
