package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class WinningRanksTest {

    @Test
    @DisplayName("랭크별 당첨된 티켓 수 조회")
    void getWinningRanks() {
        //given
        Map<Rank, Integer> winningResult = Collections.singletonMap(Rank.FIFTH, 1);
        WinningRanks winningRanks = new WinningRanks(winningResult);

        //when & then
        assertThat(winningRanks.getWinningRanks()).isEqualTo(winningResult);
    }

    @Test
    @DisplayName("총 상금 계산")
    void calculateTotalWinningMoney() {

        WinningRanks winningRanks = new WinningRanks(new HashMap() {{
            put(Rank.FIFTH, 3);
            put(Rank.FOURTH,2);
        }});

        assertThat(winningRanks.calculateTotalWinningMoney()).isEqualTo(new Money(115_000));
    }
}
