package lotto.domain.rank;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class RanksTest {

    @Test
    @DisplayName("총 획득 상금 계산")
    void getTotalWinnings() {
        Map<Rank, Long> statistics = new HashMap<>();
        Rank.getAllPossibleRanks().stream()
            .forEach(rank -> statistics.put(rank, 1L));
        Ranks ranks = new Ranks(statistics);

        Long expected = Rank.getAllPossibleRanks().stream()
            .mapToLong(Rank::getWinnings)
            .sum();

        assertThat(ranks.getTotalWinnings()).isEqualTo(expected);
    }
}
