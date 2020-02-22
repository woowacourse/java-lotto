package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class MatchNumberCountTest {
	@DisplayName("MatchCount 보유 당첨 횟수 와 매개변수로 들어오는 값과 일치하는지 확인하는 테스트")
	@ParameterizedTest
	@CsvSource(value = {"5,true", "4,false", "0,false", "10,false"})
	void isSameMatchCount(int match, boolean expected) {
		MatchNumberCount matchCount = MatchNumberCount.FIVE;
		assertThat(matchCount.isSameMatch(match)).isEqualTo(expected);
	}
}