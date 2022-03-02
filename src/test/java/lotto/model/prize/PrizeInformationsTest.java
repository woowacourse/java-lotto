package lotto.model.prize;

import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import lotto.model.AutoLotto;
import lotto.model.Money;
import lotto.model.number.BonusBall;
import lotto.model.number.LottoBall;
import lotto.model.number.WinningBalls;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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
		AutoLotto autoLotto = new AutoLotto(Arrays.asList(1, 2, 3, 7, 8, 9));
		MatchResult matchResult = MatchResult.of(autoLotto, winningBalls, bonusBall);
		List<MatchResult> matchResults = new ArrayList<>();
		matchResults.add(matchResult);
		PrizeInformation prizeInformation = PrizeInformation.of(matchResults, Prize.FIFTH);

		PrizeInformations prizeInformations = new PrizeInformations(List.of(prizeInformation));

		assertThat(prizeInformations.calculateEarningRate(Money.from("10000"))).isEqualTo(0.5);
	}

	@DisplayName("100000원 어치를 사고 5등 5장, 4등 1장이 당첨됐을때 수익률은 0.75이다")
	@Test
	void calculateEarningRate_100000_4th_1_5th_5() {
		AutoLotto autoLotto_5Th = new AutoLotto(Arrays.asList(1, 2, 3, 7, 8, 9));
		MatchResult matchResult_5th = MatchResult.of(autoLotto_5Th, winningBalls, bonusBall);

		AutoLotto autoLotto_4Th = new AutoLotto(Arrays.asList(1, 2, 3, 4, 8, 9));
		MatchResult matchResult_4th = MatchResult.of(autoLotto_4Th, winningBalls, bonusBall);

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
