package lotto.domain;

import org.junit.jupiter.api.Test;

class LottoResultTest {

    @Test
    void name() {
        LottoResult result = new LottoResult();
        result.plus(Rank.LOSE);
        result.plus(Rank.FIFTH);
        result.plus(Rank.FIFTH);
        System.out.println(result.summury());
    }
}