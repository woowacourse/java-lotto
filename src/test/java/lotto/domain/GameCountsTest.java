package lotto.domain;

import org.junit.jupiter.api.Test;

import lotto.exceptions.InvalidPurchaseAmountException;

import static org.junit.jupiter.api.Assertions.*;

class GameCountsTest {
    @Test
    void game_count_constructor_argument_must_be_divisible_by_1000() {
        assertThrows(InvalidPurchaseAmountException.class, () -> {
            new GameCounts(1002);
        });
    }
}