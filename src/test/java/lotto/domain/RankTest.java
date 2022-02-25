package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RankTest {

    @Test
    @DisplayName("숫자 입력 시 랭크 반환 성공")
    void insert_number_get_rank_true() {
        assertEquals(Rank.getRank(4, false), Rank.FOURTH);
    }

    @Test
    @DisplayName("숫자 입력 시 랭크 반환 실패")
    void insert_number_get_rank_false() {
        assertEquals(Rank.getRank(8, false), Rank.NOTHING);
    }
}