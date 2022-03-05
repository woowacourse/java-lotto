package domain.lottery;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import domain.generatestrategy.LotteryNumberGenerator;
import domain.Rank;
import domain.generateStrategy.LotteryNumberMockGenerator;
import domain.generatestrategy.LotteryNumberGeneratorStrategy;

public class LotteriesTest {


	@DisplayName("로또의 개수가 정해진 수 만큼 만들어지는지 확인")
	@ParameterizedTest(name = "{index} {displayName} lotteryNumber={0}")
	@ValueSource(ints = {1, 100, 50})
	void lotteries_number(final int lotteryNumber) {
		//given
		final List<Lottery> lotteriesNumber = new ArrayList<>();
		final LotteryNumberGeneratorStrategy lotteryNumberGeneratorStrategy = new LotteryNumberGenerator();
		for (int i = 0; i < lotteryNumber; i++) {
			lotteriesNumber.add(LotteryGenerator.generateLottery(lotteryNumberGeneratorStrategy.generateNumbers()));
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
		final List<Lottery> lotteriesNumbers = new ArrayList<>();
		final LotteryNumberGeneratorStrategy lotteryNumberGeneratorStrategy = new LotteryNumberMockGenerator();
		for (int i = 0; i < 6; i++) {
			lotteriesNumbers.add(LotteryGenerator.generateLottery(lotteryNumberGeneratorStrategy.generateNumbers()));
		}
		final Lotteries lotteries = Lotteries.from(lotteriesNumbers);
		//when
		final Map<Rank, Integer> rankResult = lotteries.getTheNumberOfWinners(
			WinningLottery.of(LotteryGenerator.generateLottery(Arrays.asList(1,2,3,4,5,6)),
				new LotteryNumber(7)));
		//then
		for (Rank rank : rankResult.keySet()) {
			rankResult.get(rank);
			assertThat(rankResult.get(rank)).isEqualTo(1);
		}
	}
}
