package domain.lottery;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import domain.LotteryGame;
import domain.Rank;
import domain.generateStrategy.LotteryGenerateMock;
import domain.generatestrategy.LotteryGenerateFamily;
import domain.generatestrategy.LotteryRandomGeneratorStrategy;

public class LotteriesTest {

	@DisplayName("로또의 개수가 정해진 수 만큼 만들어지는지 확인")
	@ParameterizedTest(name = "{index} {displayName} lotteryNumber={0}")
	@ValueSource(ints = {1, 100, 50})
	void lotteries_number(final int lotteryNumber) {
		//given
		final List<List<LotteryNumber>> lotteriesNumber = new ArrayList<>();
		final LotteryGenerateFamily lotteryGenerator = new LotteryRandomGeneratorStrategy();
		for (int i = 0; i < lotteryNumber; i++) {
			lotteriesNumber.add(lotteryGenerator.getNumbers());
		}
		//when
		final Lotteries lotteries = Lotteries.from(lotteriesNumber);
		//then
		assertThat(lotteries.getLotteries().size()).isEqualTo(lotteryNumber);
	}

	@Test
	@DisplayName("등수가 제대로 집계되는지 확인")
	void testRankingCount() {
		//given
		final List<List<LotteryNumber>> lotteriesNumbers = new ArrayList<>();
		final LotteryGenerateFamily lotteryGenerator = new LotteryGenerateMock();
		for (int i = 0; i < 6; i++) {
			lotteriesNumbers.add(lotteryGenerator.getNumbers());
		}
		final Lotteries lotteries = Lotteries.from(lotteriesNumbers);
		//when
		final Map<Rank, Integer> rankResult = lotteries.getTheNumberOfWinners(
			WinningLottery.of(LotteryNumberGenerator.generateLotteryNumbers(Arrays.asList(1, 2, 3, 4, 5, 6)),
				new LotteryNumber(7)));
		//then
		for (Rank rank : rankResult.keySet()) {
			rankResult.get(rank);
			assertThat(rankResult.get(rank)).isEqualTo(1);
		}
	}
}
