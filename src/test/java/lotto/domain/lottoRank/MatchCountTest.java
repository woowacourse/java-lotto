package lotto.domain.lottoRank;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class MatchCountTest {
	@Test
	void valueOf_WithinRankMatchCount_ReturnInstance() {
		int matchCount = 4;

		MatchCount actual = MatchCount.valueOf(matchCount);

		assertThat(actual).isEqualTo(MatchCount.FOUR);
	}

	@ParameterizedTest
	@ValueSource(ints = {0, 1, 2})
	void valueOf_OutOfRankMatchCount_ReturnMissInstance(int value) {
		MatchCount actual = MatchCount.valueOf(value);

		assertThat(actual).isEqualTo(MatchCount.MISS);
	}
}
