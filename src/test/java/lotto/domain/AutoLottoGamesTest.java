package lotto.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AutoLottoGamesTest {
    @Test
    void is_contain_proper_game_counts() {
        assertThat(new AutoLottoGames(new TotalCount(PurchaseAmount.of("3000"))).size()).isEqualTo(3);
    }
}
