package domain;

import static org.assertj.core.api.AssertionsForClassTypes.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@DisplayName("Rank 테스트")
class RankTest {

	@DisplayName("원하는 등수가 반환되는지 확인")
	@ParameterizedTest(name = "{index} {displayName} winningCount={0} hasBonusBall={1}")
	@CsvSource(value = {"6, false, FIRST", "5, true, SECOND", "5, false, THIRD", "4, false, FOURTH", "3, false, FIFTH", "2, false, NONE", "2, true, NONE"})
	void getProperRank(final int winningCount, final boolean hasBonusBall, final String RankName) {
		final Rank expectedRank = Rank.getRank(winningCount, hasBonusBall);

		assertThat(expectedRank).isEqualTo(Rank.valueOf(RankName));
	}

	@Test
	@DisplayName("NONE 등수를 제외한 나머지 등수가 반환되는지 확인")
	void getRanksExceptNoneRank() {
		final List<Rank> ranks = Rank.getValuesExceptNoneRank();

		assertAll(
			() -> assertThat(ranks.size()).isEqualTo(5),
			() -> assertThat(ranks.contains(Rank.NONE)).isFalse()
		);
	}
}