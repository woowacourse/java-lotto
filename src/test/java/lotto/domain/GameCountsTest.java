package lotto.domain;

import org.junit.jupiter.api.Test;

import lotto.exceptions.PurchaseAmountException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class GameCountsTest {
    @Test
    void how_many_games_created() {
        assertThat(new GameCounts(PurchaseAmount.of("5000")).getGameCounts()).isEqualTo(5);
    }

    @Test
    void game_counts_constructor_argument_must_be_divisible_by_1000() {
        assertThrows(PurchaseAmountException.class, () -> {
            new GameCounts(PurchaseAmount.of("1002"));
        });
    }
}