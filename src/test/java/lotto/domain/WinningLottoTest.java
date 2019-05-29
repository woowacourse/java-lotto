package lotto.domain;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class WinningLottoTest {
    WinningLotto winningLotto = WinningLotto.create(Arrays.asList(1,2,3,4,5,6));

    @Test
    void 두개_이하_일치_로또() {
        LottoTicket lotto = LottoTicket.create(Arrays.asList(11,2,3,8,9,10));
        assertThat(winningLotto.checkLottoRank(lotto))
                .isEqualTo(LottoRank.rankOf(2));
    }

    @Test
    void 세개_일치_로또() {
        LottoTicket lotto = LottoTicket.create(Arrays.asList(1,2,3,8,9,10));
        assertThat(winningLotto.checkLottoRank(lotto))
                .isEqualTo(LottoRank.rankOf(3));
    }
}
