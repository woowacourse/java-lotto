package lotto.domain;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class RankTest {

    @Test
    @DisplayName("숫자 입력 시 랭크 반환 - 2등")
    void insert_number_get_rank_second() {
        assertEquals(Rank.of(5, true), Rank.SECOND);
    }

    @Test
    @DisplayName("숫자 입력 시 랭크 반환 - 3등")
    void insert_number_get_rank_third() {
        assertEquals(Rank.of(5, false), Rank.THIRD);
    }

    @Test
    @DisplayName("숫자 입력 시 랭크 반환 - 4등")
    void insert_number_get_rank_fourth() {
        assertEquals(Rank.of(4, false), Rank.FOURTH);
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 3, 4})
    @DisplayName("정렬 된 랭크 반환")
    void sorted_rank(int input) {
        List<Rank> sortedRanks = List.of(Rank.FIFTH, Rank.FOURTH, Rank.THIRD, Rank.SECOND, Rank.FIRST);

        assertThat(Rank.getSortedRanks().get(input)).isEqualTo(sortedRanks.get(input));
    }
}
