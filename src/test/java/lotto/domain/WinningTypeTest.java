package lotto.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.*;

class WinningTypeTest {

	@ParameterizedTest
	@CsvSource(value = {"3,false,FIFTH_PLACE", "3,true,FIFTH_PLACE", "5,false,THIRD_PLACE",
			"5,true,SECOND_PLACE", "6,false,FIRST_PLACE", "6,true,FIRST_PLACE",
			"2,false,NONE", "0,false,NONE"})
	void getWinningType(int sameNumberCount, boolean bonus, String expected) {

		// when
		WinningType result = WinningType.getWinningType(sameNumberCount, bonus);

		// then
		Assertions.assertThat(result.toString())
				.isEqualTo(expected);
	}
}