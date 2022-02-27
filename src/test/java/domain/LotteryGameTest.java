package domain;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import java.util.Arrays;
import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import domain.generateStrategy.LotteryGenerateMock;
import domain.generatestrategy.LotteryRandomGeneratorStrategy;

@DisplayName("LotteryGame 테스트")
public class LotteryGameTest {

	@DisplayName("입력한 로또 개수 만큼 로또가 자동으로 생성되는지 확인")
	@ParameterizedTest(name = "{index} {displayName} lotteriesToCreate={0}")
	@ValueSource(ints = {1, 100, 50})
	void createLotteries(final int lotteriesToCreate) {
		final LotteryGame lotteryGame = new LotteryGame(new PurchaseAmount(lotteriesToCreate * 1000),
			new LotteryRandomGeneratorStrategy());

		assertThat(lotteryGame.getLotteries().size()).isEqualTo(lotteriesToCreate);
	}

	@DisplayName("등수가 제대로 집계되는지 확인")
	@ParameterizedTest(name = "{index} {displayName} purchaseAmount={0}, rank={1}")
	@CsvSource(value = {"6000, 1, FIRST", "100000, 2, SECOND", "1000, 5, FIFTH"})
	void testRankingCount(final int purchaseAmount, final int expectedRank, final String rankName) {
		final LotteryGame lotteryGame = initRankingTest(purchaseAmount, expectedRank);

		Map<Rank, Integer> rankResult = lotteryGame.makeWinner();
		Rank actualRank = Rank.valueOf(rankName);

		assertThat(rankResult.get(actualRank)).isEqualTo(purchaseAmount / 1000);
	}

	@DisplayName("승률이 제대로 집계되는지 확인")
	@ParameterizedTest(name = "{index} {displayName} purchaseAmount={0}, rank={1}")
	@CsvSource(value = {"6000, 1, 2000000000", "100000, 2, 30000000", "1000, 5, 5000"})
	void testRankingPercent(final int purchaseAmount, final int expectedRank, final int prize) {
		final LotteryGame lotteryGame = initRankingTest(purchaseAmount, expectedRank);

		Map<Rank, Integer> rankResult = lotteryGame.makeWinner();
		double percent = lotteryGame.makeRankingPercent(rankResult);

		assertThat(percent).isEqualTo((double)(prize * (purchaseAmount / 1000)) / purchaseAmount);
	}

	private LotteryGame initRankingTest(final int purchaseAmount, final int expectedRank) {
		final LotteryGame lotteryGame = new LotteryGame(new PurchaseAmount(purchaseAmount),
			new LotteryGenerateMock(expectedRank, purchaseAmount / 1000));
		lotteryGame.createWinningLottery(Arrays.asList(1, 2, 3, 4, 5, 6), 7);
		return lotteryGame;
	}

}
