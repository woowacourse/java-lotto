package lotto.domain;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class ResultTest {
    @Test
    void Rank에_따른_당첨_개수_확인() {
        Map<Rank, Integer> result = new HashMap<>();
        result.put(Rank.FIRST, 1);

        assertThat(new Result(result).get(Rank.FIRST)).isEqualTo(1);
    }

    @Test
    void 해당_Rank가_당첨되지_않은_경우() {
        Map<Rank, Integer> map = new HashMap<>();
        map.put(Rank.FIRST, 1);
        Result result = new Result(map);

        assertThat(result.get(Rank.SECOND)).isEqualTo(0);
        assertThat(result.get(Rank.THIRD)).isEqualTo(0);
        assertThat(result.get(Rank.FOURTH)).isEqualTo(0);
        assertThat(result.get(Rank.MISS)).isEqualTo(0);
    }

    @Test
    void 수익률_계산() {
        Map<Rank, Integer> map = new HashMap<>();
        map.put(Rank.FOURTH, 1);
        Result result = new Result(map);

        assertThat(result.calculateEarningsRate(new Payment(10_000))).isEqualTo(0.5);
    }
}
