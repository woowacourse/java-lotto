package lotto.domain.result;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LottoRankTest {
    private static final boolean TRUE = true;
    private static final boolean FALSE = false;

    @Test
    void 일등_확인() {
        assertEquals(LottoRank.valueOf(6, FALSE), LottoRank.FIRST);
        assertEquals(LottoRank.valueOf(6, TRUE), LottoRank.FIRST);
    }

    @Test
    void 이등_확인() {
        assertEquals(LottoRank.valueOf(5, TRUE), LottoRank.SECOND);
        assertEquals(LottoRank.valueOf(5, TRUE), LottoRank.SECOND);
    }

    @Test
    void 삼등_확인() {
        assertEquals(LottoRank.valueOf(5, FALSE), LottoRank.THIRD);
    }
    @Test
    void 사등_확인() {
        assertEquals(LottoRank.valueOf(4, FALSE), LottoRank.FOURTH);
        assertEquals(LottoRank.valueOf(4, TRUE), LottoRank.FOURTH);
    }

    @Test
    void 오등_확인() {
        assertEquals(LottoRank.valueOf(3, FALSE), LottoRank.FIFTH);
        assertEquals(LottoRank.valueOf(3, TRUE), LottoRank.FIFTH);
    }

    @Test
    void 꽝_확인() {
        assertEquals(LottoRank.valueOf(2, FALSE), LottoRank.MISS);
        assertEquals(LottoRank.valueOf(1, FALSE), LottoRank.MISS);
        assertEquals(LottoRank.valueOf(0, FALSE), LottoRank.MISS);
        assertEquals(LottoRank.valueOf(2, TRUE), LottoRank.MISS);
        assertEquals(LottoRank.valueOf(1, TRUE), LottoRank.MISS);
        assertEquals(LottoRank.valueOf(0, TRUE), LottoRank.MISS);
    }
}
