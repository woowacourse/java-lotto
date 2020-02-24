package lotto.domain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@SuppressWarnings("NonAsciiCharacters")
public class WinningPrizeTest {
	@ParameterizedTest
	@CsvSource(value = {"6,false,FIRST", "5,true,SECOND", "3,true,FIFTH", "2,false,NO_PRIZE"})
	void 등수_확인(int matchCount, boolean bonusMatch, String expected) {
		WinningPrize prize = WinningPrize.of(matchCount, bonusMatch);
		assertEquals(expected, prize.name());
	}
}
