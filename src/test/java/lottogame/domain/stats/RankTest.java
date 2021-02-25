package lottogame.domain.stats;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RankTest {
    @ParameterizedTest
    @DisplayName("올바른 등수를 반환하는지 확인")
    @CsvSource(value = {
            "0:true:NOT_FOUND", "0:true:NOT_FOUND",
            "1:true:NOT_FOUND", "1:false:NOT_FOUND",
            "2:true:NOT_FOUND", "2:false:NOT_FOUND",
            "3:true:FIFTH", "3:false:FIFTH",
            "4:true:FOURTH", "4:false:FOURTH",
            "5:true:SECOND", "5:false:THIRD",
            "6:false:FIRST"}, delimiter = ':')
    void of(int countInput, boolean booleanInput, String rankInput) {
        Rank rank = Rank.of(countInput, booleanInput);
        assertEquals(rank, Rank.valueOf(rankInput));
    }

    @Test
    @DisplayName("2등인지 확인")
    void isSecond() {
        Rank rank = Rank.of(5, true);
        assertTrue(rank.isSecond());
    }

    @ParameterizedTest
    @DisplayName("등수가 있는지 확인")
    @CsvSource(value = {
            "NOT_FOUND:false", "FIFTH:true",
            "FOURTH:true", "THIRD:true",
            "SECOND:true", "FIRST:true"}, delimiter = ':')
    void isFound(String rankInput, boolean expectedValue) {
        Rank rank = Rank.valueOf(rankInput);
        assertEquals(rank.isFound(), expectedValue);
    }
}
