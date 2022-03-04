package lotto.model.prize;

import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import lotto.model.lotto.Lotto;
import lotto.model.lotto.BonusBall;
import lotto.model.lotto.LottoBall;
import lotto.model.lotto.WinningBalls;

public class PrizeInformationTest {
	WinningBalls winningBalls;
	BonusBall bonusBall;

	@BeforeEach
	void initializeLotto() {
		winningBalls = WinningBalls.from(Arrays.asList("1", "2", "3", "4", "5", "6"));
		bonusBall = BonusBall.from(LottoBall.from("10"), winningBalls);

	}

	@DisplayName("5등이 3장 당첨됐을때 당첨금은 15000원이다")
	@Test
	void pickAmount_5th_3() {
		List<String> lottoBalls = Arrays.asList("1", "2", "3", "7", "8", "9");
		Lotto lotto = Lotto.fromManual(lottoBalls);
		MatchResult matchResult = MatchResult.of(lotto, winningBalls, bonusBall);
		List<MatchResult> matchResults = new ArrayList<>();
		for (int i = 0; i < 3; i++) {
			matchResults.add(matchResult);
		}

		PrizeInformation prizeInformation = PrizeInformation.of(matchResults, Prize.FIFTH);

		assertThat(prizeInformation.pickAmount()).isEqualTo(15000);
	}

	@DisplayName("5등이 2장 당첨됐을때 당첨금은 10000원이다")
	@Test
	void pickAmount_5th_2() {
		List<String> lottoBalls = Arrays.asList("1", "2", "3", "7", "8", "9");
		Lotto lotto = Lotto.fromManual(lottoBalls);
		MatchResult matchResult = MatchResult.of(lotto, winningBalls, bonusBall);
		List<MatchResult> matchResults = new ArrayList<>();
		for (int i = 0; i < 2; i++) {
			matchResults.add(matchResult);
		}

		PrizeInformation prizeInformation = PrizeInformation.of(matchResults, Prize.FIFTH);

		assertThat(prizeInformation.pickAmount()).isEqualTo(10000);
	}

	@DisplayName("4등이 3장 당첨됐을때 당첨금은 150000원이다")
	@Test
	void pickAmount_4th_3() {
		List<String> lottoBalls = Arrays.asList("1", "2", "3", "4", "7", "8");
		Lotto lotto = Lotto.fromManual(lottoBalls);
		MatchResult matchResult = MatchResult.of(lotto, winningBalls, bonusBall);
		List<MatchResult> matchResults = new ArrayList<>();
		for (int i = 0; i < 3; i++) {
			matchResults.add(matchResult);
		}

		PrizeInformation prizeInformation = PrizeInformation.of(matchResults, Prize.FOURTH);

		assertThat(prizeInformation.pickAmount()).isEqualTo(150000);
	}
}
