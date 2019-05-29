package lotto.domain;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class LottoResultTest {
    LottoTicket winning = LottoTicket.create(Arrays.asList(1,2,3,4,5,6));

    @Test
    void 두개_이하_일치_로또() {
        LottoTicket user = LottoTicket.create(Arrays.asList(11,2,3,8,9,10));
        assertThat(LottoRank.rankOf(winning.countOfMatch(user))).isEqualTo(LottoRank.FAIL);
    }

    @Test
    void 세개_일치_로또() {
        LottoTicket user = LottoTicket.create(Arrays.asList(1,2,3,8,9,10));
        assertThat(LottoRank.rankOf(winning.countOfMatch(user))).isEqualTo(LottoRank.FOURTH);

    }
}
