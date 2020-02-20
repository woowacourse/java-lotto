package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

class ResultTest {

    @Test
    void 랭크별_당첨된_티켓_수_추가() {
        //given
        Result result = new Result(new HashMap<>());
        //when
        Map<Rank, Integer> winningResults = result.addWinningResult(Rank.FIFTH, 1);
        //then
        assertThat(winningResults.size()).isEqualTo(1);
        assertThat(winningResults).isEqualTo(Collections.singletonMap(Rank.FIFTH, 1));
    }
}
