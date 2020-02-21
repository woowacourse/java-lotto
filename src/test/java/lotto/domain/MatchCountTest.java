package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class MatchCountTest {
	@ParameterizedTest
	@CsvSource(value = {"5,true", "4,false", "0,false", "10,false"})
	void isSameMatchCount(int match, boolean expected) {
		MatchCount matchCount = MatchCount.FIVE;
		assertThat(matchCount.isSameMatch(match)).isEqualTo(expected);
	}
}