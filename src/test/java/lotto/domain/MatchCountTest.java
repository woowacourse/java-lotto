package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

class MatchCountTest {
	@Test
	void isSameMatchCount() {
		MatchCount matchCount = MatchCount.FIVE;
		assertThat(matchCount.isSameMatch(5)).isTrue();
	}
}