package domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RankTest {

	@DisplayName("3등 추출 테스트")
	@Test
	void of_rank_third() {
		Rank rank = Rank.of(5, false);

		Assertions.assertTrue(rank == Rank.THIRD);
	}

	@DisplayName("2등 추출 테스트")
	@Test
	void of_rank_second() {
		Rank rank = Rank.of(5, true);

		Assertions.assertTrue(rank == Rank.SECOND);
	}

	@DisplayName("로또번호 4개 맞고, 보너스도 맞았을 경우 결과값 3등")
	@Test
	void of_fourth_bonus_different() {
		Rank rank = Rank.of(4, true);

		Assertions.assertTrue(rank == Rank.FOURTH);
	}
}