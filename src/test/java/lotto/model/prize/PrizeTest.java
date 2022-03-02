package lotto.model.prize;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import lotto.model.Lotto;
import lotto.model.number.BonusBall;
import lotto.model.number.LottoBall;
import lotto.model.number.WinningBalls;

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
		List<LottoBall> lottoBalls = Arrays.asList(
				LottoBall.from("1"),
				LottoBall.from("2"),
				LottoBall.from("3"),
				LottoBall.from("7"),
				LottoBall.from("8"),
				LottoBall.from("9")
		);
		Lotto lotto = new Lotto(lottoBalls);

		MatchResult matchResult = MatchResult.of(lotto, winningBalls, bonusBall);

		assertThat(Prize.getPrize(matchResult)).isEqualTo(Prize.FIFTH);
	}

	@DisplayName("4개가 일치하면 4등을 반환한다")
	@Test
	void match_4_fourth() {
		List<LottoBall> lottoBalls = Arrays.asList(
				LottoBall.from("1"),
				LottoBall.from("2"),
				LottoBall.from("3"),
				LottoBall.from("4"),
				LottoBall.from("8"),
				LottoBall.from("9")
		);
		Lotto lotto = new Lotto(lottoBalls);

		MatchResult matchResult = MatchResult.of(lotto, winningBalls, bonusBall);

		assertThat(Prize.getPrize(matchResult)).isEqualTo(Prize.FOURTH);
	}

	@DisplayName("5개가 일치하면 3등을 반환한다")
	@Test
	void match_5_third() {
		List<LottoBall> lottoBalls = Arrays.asList(
				LottoBall.from("1"),
				LottoBall.from("2"),
				LottoBall.from("3"),
				LottoBall.from("4"),
				LottoBall.from("5"),
				LottoBall.from("9")
		);
		Lotto lotto = new Lotto(lottoBalls);

		MatchResult matchResult = MatchResult.of(lotto, winningBalls, bonusBall);

		assertThat(Prize.getPrize(matchResult)).isEqualTo(Prize.THIRD);
	}

	@DisplayName("6개가 일치하면 1등을 반환한다")
	@Test
	void match_6_first() {
		List<LottoBall> lottoBalls = Arrays.asList(
				LottoBall.from("1"),
				LottoBall.from("2"),
				LottoBall.from("3"),
				LottoBall.from("4"),
				LottoBall.from("5"),
				LottoBall.from("6")
		);
		Lotto lotto = new Lotto(lottoBalls);

		MatchResult matchResult = MatchResult.of(lotto, winningBalls, bonusBall);

		assertThat(Prize.getPrize(matchResult)).isEqualTo(Prize.FIRST);
	}

	@DisplayName("5개가 일치하고 보너스가 일치하면 2등을 반환한다")
	@Test
	void match_5_bonus_second() {
		List<LottoBall> lottoBalls = Arrays.asList(
				LottoBall.from("1"),
				LottoBall.from("2"),
				LottoBall.from("3"),
				LottoBall.from("4"),
				LottoBall.from("5"),
				LottoBall.from("10")
		);
		Lotto lotto = new Lotto(lottoBalls);

		MatchResult matchResult = MatchResult.of(lotto, winningBalls, bonusBall);

		assertThat(Prize.getPrize(matchResult)).isEqualTo(Prize.SECOND);
	}

}
