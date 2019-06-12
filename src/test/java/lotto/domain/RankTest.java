package lotto.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RankTest {
    @Test
    void 순위() {
        assertEquals(Rank.FIRST, Rank.rank(6, false));
    }

    @Test
    void 순위2() {
        assertEquals(Rank.SECOND, Rank.rank(5, true));
    }

    @Test
    void 순위3() {
        assertEquals(Rank.THIRD, Rank.rank(5, false));
    }

    @Test
    void 순위4() {
        assertEquals(Rank.FOURTH, Rank.rank(4, false));
    }

    @Test
    void 순위5() {
        assertEquals(Rank.FIFTH, Rank.rank(3, false));
    }

    @Test
    void 순위6() {
        assertEquals(Rank.LOSE, Rank.rank(2, false));
    }
}