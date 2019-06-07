package lotto.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RankTest {
    @Test
    void 순위() {
        assertEquals(Rank.FIST, Rank.rank(6, false));
    }

    @Test
    void 순위2() {
        assertEquals(Rank.SECOND, Rank.rank(5, true));
    }
}