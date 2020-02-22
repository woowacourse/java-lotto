package lotto.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LottoRankTest {
    @Test
    @DisplayName("세개 미만 숫자 맞췄을 경우 false 반환")
    void isCorrectNumberOverThree() {
        assertFalse(LottoRank.isCorrectNumberOverThree(2));
        assertTrue(LottoRank.isCorrectNumberOverThree(3));
    }
}
