package model.result;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class RankTest {
    @DisplayName("match메서드_일치하는경우_테스트")
    @Test
    void match메서드_일치하는경우_테스트() {
        assertTrue(Rank.RANK1.match(6, false));
        assertTrue(Rank.RANK2.match(5, true));
        assertTrue(Rank.RANK3.match(5, false));
        assertTrue(Rank.RANK4.match(4, false));
        assertTrue(Rank.RANK5.match(3, false));
        assertTrue(Rank.MISS.match(0, false));
    }

    @DisplayName("match메서드_일치하지않는경우_테스트")
    @Test
    void match메서드_일치하지않는경우_테스트() {
        assertFalse(Rank.RANK1.match(5, false));
        assertFalse(Rank.RANK2.match(5, false));
        assertFalse(Rank.RANK3.match(5, true));
        assertFalse(Rank.RANK4.match(5, false));
        assertFalse(Rank.RANK5.match(5, false));
    }

    @DisplayName("judgeRank메서드_테스트")
    @Test
    void judgeRank메서드_테스트() {
        assertEquals(Rank.RANK1, Rank.judgeRank(6, false));
        assertEquals(Rank.RANK2, Rank.judgeRank(5, true));
        assertEquals(Rank.RANK3, Rank.judgeRank(5, false));
        assertEquals(Rank.RANK4, Rank.judgeRank(4, false));
        assertEquals(Rank.RANK5, Rank.judgeRank(3, false));
        assertEquals(Rank.MISS, Rank.judgeRank(2, false));
        assertEquals(Rank.MISS, Rank.judgeRank(0, false));
    }
}