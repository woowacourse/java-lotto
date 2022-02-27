package domain;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import java.util.Arrays;
import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import domain.generateStrategy.LotteryGenerateMock;
import domain.generatestrategy.LotteryRandomGeneratorStrategy;

@DisplayName("LotteryGame 테스트")
public class LotteryGameTest {

	@DisplayName("입력한 로또 개수 만큼 로또가 자동으로 생성되는지 확인")
	@ParameterizedTest(name = "{index} {displayName} inputMoney={0}")
	@ValueSource(ints = {1000, 100000, 50000})
	void createLotteries(final int inputMoney) {
		//given
		final LotteryGame lotteryGame = LotteryGame.of(inputMoney, new LotteryRandomGeneratorStrategy());
		final int lotteriesToCreate = inputMoney / 1000;
		//when
		//then
		assertThat(lotteryGame.getLotteries().size()).isEqualTo(lotteriesToCreate);
	}

	@Test
	@DisplayName("등수가 제대로 집계되는지 확인")
	void testRankingCount() {
		//given
		final LotteryGame lotteryGame = LotteryGame.of(6000, new LotteryGenerateMock());
		lotteryGame.createWinningLottery(Arrays.asList(1, 2, 3, 4, 5, 6), 7);
		//when
		final Map<Rank, Integer> rankResult = lotteryGame.makeWinner();
		//then
		for (Rank rank : rankResult.keySet()) {
			rankResult.get(rank);
			assertThat(rankResult.get(rank)).isEqualTo(1);
		}
	}

	@Test
	@DisplayName("승률이 제대로 집계되는지 확인")
	void testRankingPercent() {
		//given
		final LotteryGame lotteryGame = LotteryGame.of(6000, new LotteryGenerateMock());
		lotteryGame.createWinningLottery(Arrays.asList(1, 2, 3, 4, 5, 6), 7);
		//when
		final Map<Rank, Integer> rankResult = lotteryGame.makeWinner();
		final double percent = lotteryGame.makeReturnRate(rankResult);
		//then
		assertThat(percent).isEqualTo((double)2031555000 / (6 * 1000));
	}
}
