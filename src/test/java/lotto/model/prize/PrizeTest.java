package lotto.model.prize;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;

import lotto.model.Lotto;
import lotto.model.number.BonusBall;
import lotto.model.number.LottoBall;
import lotto.model.number.WinningBalls;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PrizeTest {
	WinningBalls winningBalls;
	BonusBall bonusBall;

	@BeforeEach
	void initializePrizeInformation() {
		winningBalls = WinningBalls.from(Arrays.asList("1", "2", "3", "4", "5", "6"));
		bonusBall = BonusBall.from(LottoBall.from("10"), winningBalls);
	}

	@DisplayName("3개가 일치하면 5등을 반환한다")
	@Test
	void match_3_fifth() {
		Lotto lotto = new Lotto(Arrays.asList(1, 2, 3, 7, 8, 9));

		MatchResult matchResult = MatchResult.of(lotto, winningBalls, bonusBall);

		assertThat(Prize.getPrize(matchResult)).isEqualTo(Prize.FIFTH);
	}

	@DisplayName("4개가 일치하면 4등을 반환한다")
	@Test
	void match_4_fourth() {
		Lotto lotto = new Lotto(Arrays.asList(1, 2, 3, 4, 8, 9));

		MatchResult matchResult = MatchResult.of(lotto, winningBalls, bonusBall);

		assertThat(Prize.getPrize(matchResult)).isEqualTo(Prize.FOURTH);
	}

	@DisplayName("5개가 일치하면 3등을 반환한다")
	@Test
	void match_5_third() {
		Lotto lotto = new Lotto(Arrays.asList(1, 2, 3, 4, 5, 9));

		MatchResult matchResult = MatchResult.of(lotto, winningBalls, bonusBall);

		assertThat(Prize.getPrize(matchResult)).isEqualTo(Prize.THIRD);
	}

	@DisplayName("6개가 일치하면 1등을 반환한다")
	@Test
	void match_6_first() {
		Lotto lotto = new Lotto(Arrays.asList(1, 2, 3, 4, 5, 6));

		MatchResult matchResult = MatchResult.of(lotto, winningBalls, bonusBall);

		assertThat(Prize.getPrize(matchResult)).isEqualTo(Prize.FIRST);
	}

	@DisplayName("5개가 일치하고 보너스가 일치하면 2등을 반환한다")
	@Test
	void match_5_bonus_second() {
		Lotto lotto = new Lotto(Arrays.asList(1, 2, 3, 4, 5, 10));

		MatchResult matchResult = MatchResult.of(lotto, winningBalls, bonusBall);

		assertThat(Prize.getPrize(matchResult)).isEqualTo(Prize.SECOND);
	}

}
