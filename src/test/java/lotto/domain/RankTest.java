package lotto.domain;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class RankTest {
    @Test
    void 결과가_SECOND() {
        assertThat(Rank.valueOf(5, true)).isEqualTo(Rank.SECOND);
    }

    @Test
    void 결과가_MISS() {
        assertThat(Rank.valueOf(2, false)).isEqualTo(Rank.MISS);
    }

    @Test
    void 결과가_FIRST() {
        assertThat(Rank.valueOf(6, false)).isEqualTo(Rank.FIRST);
    }

    @Test
    void 결과주기() {
        List<Rank> ranks = new ArrayList<>();
        ranks.add(Rank.FIFTH);
        ranks.add(Rank.FIRST);

        List<Integer> numbers = new ArrayList<>();
        numbers.add(5_000);
        numbers.add(2_000_000_000);

        assertThat(Rank.providePrizeResult(ranks)).isEqualTo(numbers);
    }
}
