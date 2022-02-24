package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RankTest {

    @Test
    @DisplayName("숫자 입력 시 랭크 반환")
    void insert_number_get_rank() {
        assertEquals(Rank.getRank(4, false), Rank.FOURTH);
    }
}