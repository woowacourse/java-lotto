package lotto.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LottoRankTest {
    @Test
    @DisplayName("세개 미만 숫자 맞췄을 경우 ture 반환")
    void checkNoPrize() {
        assertTrue(LottoRank.checkNoPrize(2));
        assertFalse(LottoRank.checkNoPrize(3));
    }
}
