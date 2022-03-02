package lotto.model.prize;

import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import lotto.model.Lotto;
import lotto.model.Money;
import lotto.model.number.BonusBall;
import lotto.model.number.LottoBall;
import lotto.model.number.WinningBalls;

public class PrizeInformationsTest {
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
		List<MatchResult> matchResults = new ArrayList<>();
		matchResults.add(matchResult);
		PrizeInformation prizeInformation = PrizeInformation.of(matchResults, Prize.FIFTH);

		PrizeInformations prizeInformations = new PrizeInformations(List.of(prizeInformation));

		assertThat(prizeInformations.calculateEarningRate(Money.from("10000"))).isEqualTo(0.5);
	}

	@DisplayName("100000원 어치를 사고 5등 5장, 4등 1장이 당첨됐을때 수익률은 0.75이다")
	@Test
	void calculateEarningRate_100000_4th_1_5th_5() {
		List<LottoBall> lottoBalls_5th = Arrays.asList(
				LottoBall.from("1"),
				LottoBall.from("2"),
				LottoBall.from("3"),
				LottoBall.from("7"),
				LottoBall.from("8"),
				LottoBall.from("9")
		);
		Lotto lotto_5Th = new Lotto(lottoBalls_5th);
		MatchResult matchResult_5th = MatchResult.of(lotto_5Th, winningBalls, bonusBall);

		List<LottoBall> lottoBalls_4th = Arrays.asList(
				LottoBall.from("1"),
				LottoBall.from("2"),
				LottoBall.from("3"),
				LottoBall.from("4"),
				LottoBall.from("8"),
				LottoBall.from("9")
		);
		Lotto lotto_4Th = new Lotto(lottoBalls_4th);
		MatchResult matchResult_4th = MatchResult.of(lotto_4Th, winningBalls, bonusBall);

		List<MatchResult> matchResults = new ArrayList<>();
		for (int i = 0; i < 5; i++) {
			matchResults.add(matchResult_5th);
		}
		matchResults.add(matchResult_4th);
		PrizeInformation prizeInformation_5th = PrizeInformation.of(matchResults, Prize.FIFTH);
		PrizeInformation prizeInformation_4th = PrizeInformation.of(matchResults, Prize.FOURTH);

		PrizeInformations prizeInformations = new PrizeInformations(
				Arrays.asList(prizeInformation_4th, prizeInformation_5th));

		assertThat(prizeInformations.calculateEarningRate(Money.from("100000"))).isEqualTo(0.75);
	}
}
