package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LottoResultTest {

    @Test
    @DisplayName("Rank 입력 시 당첨 수 증가")
    void add_rank() {
        LottoResult lottoResult = new LottoResult();

        lottoResult.increaseRankCount(Rank.FIRST);

        assertEquals(lottoResult.getLottoResult().get(Rank.FIRST), 1);
    }
}
