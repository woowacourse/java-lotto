package lotto.domain;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class RankTest {

    @Test
    @DisplayName("숫자 입력 시 랭크 반환")
    void insert_number_get_rank() {
        assertEquals(Rank.of(4, false), Rank.FOURTH);
    }

    @Test
    @DisplayName("총 상금 계산")
    void calculate_total_prize() {
        Map<Rank,Integer> lottoResult = new HashMap<>();

        Arrays.stream(Rank.values())
            .forEach((rank -> lottoResult.put(rank, 0)));
        lottoResult.put(Rank.FIRST,1);
        lottoResult.put(Rank.SECOND,1);
        lottoResult.put(Rank.FOURTH,1);

        assertThat(Rank.getTotalWinningPrize(lottoResult).getMoney()).isEqualTo(2_030_050_000);
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 3, 4})
    @DisplayName("정렬 된 랭크 반환")
    void sorted_rank(int input) {
        List<Rank> sortedRanks = List.of(Rank.FIFTH, Rank.FOURTH, Rank.THIRD, Rank.SECOND, Rank.FIRST);

        assertThat(Rank.getSortedRanks().get(input)).isEqualTo(sortedRanks.get(input));
    }
}
