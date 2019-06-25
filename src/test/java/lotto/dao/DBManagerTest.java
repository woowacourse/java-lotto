package lotto.dao;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DBManagerTest {

    @Test
    void lastRound() {
        assertEquals(1, DBManager.lastRound());
    }
}