package lotto.domain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RankTest {

    @Test
    @DisplayName("숫자 입력 시 랭크 반환")
    void insert_number_get_rank() {
        assertEquals(Rank.getInstance(4, false), Rank.FOURTH);
    }
}
