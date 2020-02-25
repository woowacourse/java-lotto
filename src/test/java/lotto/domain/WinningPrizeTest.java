package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WinningPrizeTest {
	@ParameterizedTest
	@CsvSource(value = {"6,false,1등", "5,true,2등(보너스볼 일치)", "3,true,5등", "2,false,미당첨"})
	@DisplayName("등수를 올바르게 출력하는지 확인")
	void checkPrize(int matchCount, boolean bonusMatch, String expected) {
		WinningPrize prize = WinningPrize.of(matchCount, bonusMatch);
		assertEquals(expected, prize.getDescription());
	}
}
