package lotto.model.prize;

import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import lotto.model.Money;
import lotto.model.lotto.BonusBall;
import lotto.model.lotto.Lotto;
import lotto.model.lotto.LottoBall;
import lotto.model.lotto.WinningBalls;

public class PrizeInformationTest {
	WinningBalls winningBalls;
	BonusBall bonusBall;

	@BeforeEach
	void initializePrizeInformation() {
		winningBalls = WinningBalls.from(Arrays.asList("1", "2", "3", "4", "5", "6"));
		bonusBall = BonusBall.from(LottoBall.from("10"), winningBalls);
	}

	@DisplayName("10000원 어치를 사고 5등이 1장 당첨됐을때 수익률은 0.5이다")
	@Test
	void calculateEarningRate_10000_5th_1() {
		List<String> lottoBalls = Arrays.asList("1", "2", "3", "7", "8", "9");
		Lotto lotto = Lotto.fromManual(lottoBalls);

		MatchResult matchResult = MatchResult.of(lotto, winningBalls, bonusBall);
		List<MatchResult> matchResults = new ArrayList<>();
		matchResults.add(matchResult);

		PrizeInformation prizeInformation = PrizeInformation.from(matchResults);

		assertThat(prizeInformation.calculateEarningRate(Money.from("10000"))).isEqualTo(0.5);
	}

	@DisplayName("100000원 어치를 사고 5등 5장, 4등 1장이 당첨됐을때 수익률은 0.75이다")
	@Test
	void calculateEarningRate_100000_4th_1_5th_5() {
		List<String> lottoBalls_5th = Arrays.asList("1", "2", "3", "7", "8", "9");
		Lotto lotto_5Th = Lotto.fromManual(lottoBalls_5th);

		MatchResult matchResult_5th = MatchResult.of(lotto_5Th, winningBalls, bonusBall);

		List<String> lottoBalls_4th = Arrays.asList("1", "2", "3", "4", "8", "9");
		Lotto lotto_4Th = Lotto.fromManual(lottoBalls_4th);

		MatchResult matchResult_4th = MatchResult.of(lotto_4Th, winningBalls, bonusBall);

		List<MatchResult> matchResults = new ArrayList<>();
		for (int i = 0; i < 5; i++) {
			matchResults.add(matchResult_5th);
		}
		matchResults.add(matchResult_4th);

		PrizeInformation prizeInformation = PrizeInformation.from(matchResults);

		assertThat(prizeInformation.calculateEarningRate(Money.from("100000"))).isEqualTo(0.75);
	}
}
