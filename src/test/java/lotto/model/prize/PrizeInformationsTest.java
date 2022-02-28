package lotto.model.prize;

import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import lotto.model.Lotto;
import lotto.model.Money;
import lotto.model.number.BonusNumber;
import lotto.model.number.Number;
import lotto.model.number.WinningNumbers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PrizeInformationsTest {
	WinningNumbers winningNumbers;
	BonusNumber bonusNumber;

	@BeforeEach
	void initializePrizeInformation() {
		winningNumbers = WinningNumbers.from(Arrays.asList("1", "2", "3", "4", "5", "6"));
		bonusNumber = BonusNumber.from(Number.from("10"), winningNumbers);
	}

	@DisplayName("10000원 어치를 사고 5등이 1장 당첨됐을때 수익률은 0.5이다")
	@Test
	void calculateEarningRate_10000_5th_1() {
		Lotto lotto = new Lotto(Arrays.asList(1, 2, 3, 7, 8, 9));
		MatchResult matchResult = MatchResult.of(lotto, winningNumbers, bonusNumber);
		List<MatchResult> matchResults = new ArrayList<>();
		matchResults.add(matchResult);
		PrizeInformation prizeInformation = PrizeInformation.of(matchResults, Prize.FIFTH);

		PrizeInformations prizeInformations = new PrizeInformations(List.of(prizeInformation));

		assertThat(prizeInformations.calculateEarningRate(Money.from("10000"))).isEqualTo(0.5);
	}

	@DisplayName("100000원 어치를 사고 5등 5장, 4등 1장이 당첨됐을때 수익률은 0.75이다")
	@Test
	void calculateEarningRate_100000_4th_1_5th_5() {
		Lotto lotto_5th = new Lotto(Arrays.asList(1, 2, 3, 7, 8, 9));
		MatchResult matchResult_5th = MatchResult.of(lotto_5th, winningNumbers, bonusNumber);

		Lotto lotto_4th = new Lotto(Arrays.asList(1, 2, 3, 4, 8, 9));
		MatchResult matchResult_4th = MatchResult.of(lotto_4th, winningNumbers, bonusNumber);

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
