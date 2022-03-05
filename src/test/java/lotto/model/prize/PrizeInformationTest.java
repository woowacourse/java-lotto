package lotto.model.prize;

import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import lotto.model.Money;
import lotto.model.lotto.Lottos;
import lotto.model.lotto.WinningBalls;

public class PrizeInformationTest {
	WinningBalls winningBalls;

	@BeforeEach
	void initializePrizeInformation() {
		winningBalls = WinningBalls.from(Arrays.asList("1", "2", "3", "4", "5", "6"), "10");
	}

	@DisplayName("10000원 어치를 사고 5등이 1장 당첨됐을때 수익률은 0.5이다")
	@Test
	void calculateEarningRate_10000_5th_1() {
		List<String> lottoBalls = Arrays.asList("1", "2", "3", "7", "8", "9");
		List<List<String>> input = List.of(lottoBalls);
		Lottos lottos = Lottos.purchase(1, 1, input);

		PrizeInformation prizeInformation = PrizeInformation.from(lottos, winningBalls);

		assertThat(prizeInformation.calculateEarningRate(Money.from("10000"))).isEqualTo(0.5);
	}

	@DisplayName("100000원 어치를 사고 5등 5장, 4등 1장이 당첨됐을때 수익률은 0.75이다")
	@Test
	void calculateEarningRate_100000_4th_1_5th_5() {
		List<String> lottoBalls_5th = Arrays.asList("1", "2", "3", "7", "8", "9");
		List<String> lottoBalls_4th = Arrays.asList("1", "2", "3", "4", "8", "9");
		List<List<String>> input = new ArrayList<>();
		for (int i = 0; i < 5; i++) {
			input.add(lottoBalls_5th);
		}
		input.add(lottoBalls_4th);
		Lottos lottos = Lottos.purchase(1, 1, input);

		PrizeInformation prizeInformation = PrizeInformation.from(lottos, winningBalls);

		assertThat(prizeInformation.calculateEarningRate(Money.from("100000"))).isEqualTo(0.75);
	}
}
