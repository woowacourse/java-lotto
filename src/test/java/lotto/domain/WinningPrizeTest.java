package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WinningPrizeTest {
	@ParameterizedTest
	@CsvSource(value = {"6,false,FIRST", "5,true,SECOND", "3,true,FIFTH", "2,false,NO_PRIZE"})
	@DisplayName("등수를 올바르게 출력하는지 확인")
	void checkPrize(int matchCount, boolean bonusMatch, String expected) {
		WinningPrize prize = WinningPrize.of(matchCount, bonusMatch);
		assertEquals(expected, prize.name());
	}
}
