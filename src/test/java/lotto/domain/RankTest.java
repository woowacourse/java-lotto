package lotto.domain;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

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

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 3, 4})
    @DisplayName("정렬 된 랭크 반환")
    void sorted_rank_correct(int input) {
        List<Rank> sortedRanks = List.of(Rank.FIFTH, Rank.FOURTH, Rank.THIRD, Rank.SECOND, Rank.FIRST);
        assertEquals(sortedRanks.get(input), sortedRanks.get(input));
    }
}
