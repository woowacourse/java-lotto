package lotto.domain;

import org.junit.jupiter.api.Test;

import lotto.domain.exceptions.PurchaseAmountException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TotalCountTest {
    @Test
    void how_many_games_created() {
        assertThat(new TotalCount(PurchaseAmount.is("5000")).getTotalCount()).isEqualTo(5);
    }

    @Test
    void game_counts_constructor_argument_must_be_divisible_by_1000() {
        assertThrows(PurchaseAmountException.class, () -> {
            new TotalCount(PurchaseAmount.is("1002"));
        });
    }
}