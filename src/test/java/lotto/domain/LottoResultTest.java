package lotto.domain;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static lotto.domain.Rank.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class LottoResultTest {
    @Test
    void 총이익률_확인() {
        List<Rank> ranks = Arrays.asList(FOURTH,FOURTH);
        LottoResult lottoResult = new LottoResult(ranks);
        assertEquals(lottoResult.findYield(10000),10);
    }
}
