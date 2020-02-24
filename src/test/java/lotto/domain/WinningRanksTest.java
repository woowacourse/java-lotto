package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

class WinningRanksTest {

    @Test
    void 랭크별_당첨된_티켓_수_추가() {
        //given
        WinningRanks winningRanks = new WinningRanks(new HashMap<>());
        //when
        Map<Rank, Integer> winningResults = winningRanks.addWinningRanks(Rank.FIFTH);
        //then
        assertThat(winningResults.size()).isEqualTo(1);
        assertThat(winningResults).isEqualTo(Collections.singletonMap(Rank.FIFTH, 1));
    }

    @Test
    void 랭크별_당첨된_티켓_수_조회() {
        //given
        Map<Rank, Integer> winningResult = Collections.singletonMap(Rank.FIFTH, 1);
        WinningRanks winningRanks = new WinningRanks(winningResult);

        //when & then
        assertThat(winningRanks.getWinningRanks()).isEqualTo(winningResult);
    }

    @Test
    void 총_상금_계산() {

        WinningRanks winningRanks = new WinningRanks(new HashMap() {{
            put(Rank.FIFTH, 3);
            put(Rank.FOURTH,2);
        }});

        assertThat(winningRanks.calculateTotalWinningMoney()).isEqualTo(new Money(115_000));
    }
}
