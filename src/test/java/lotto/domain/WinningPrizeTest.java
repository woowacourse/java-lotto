package lotto.domain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class WinningPrizeTest {
	@ParameterizedTest
	@CsvSource(value = {"6,false,1등", "5,true,2등(보너스볼 일치)", "3,true,5등", "2,false,미당첨"})
	void 등수_확인(int matchCount, boolean bonusMatch, String expected) {
		WinningPrize prize = WinningPrize.of(matchCount, bonusMatch);
		assertEquals(expected, prize.getPrizeDescription());
	}
}
