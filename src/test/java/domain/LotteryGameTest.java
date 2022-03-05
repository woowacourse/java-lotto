package domain;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import domain.factory.LotteryGenerateMockFactory;
import domain.factory.LotteryNumberFactory;
import domain.generateStrategy.LotteryGenerateMock;
import domain.generatestrategy.ManualLotteryGeneratorStrategy;
import utils.Parser;

@DisplayName("LotteryGame 테스트")
public class LotteryGameTest {

	@DisplayName("입력한 로또 개수 만큼 로또가 자동으로 생성되는지 확인")
	@ParameterizedTest(name = "{index} {displayName} lotteriesToCreate={0}")
	@ValueSource(ints = {1, 100, 50})
	void createLotteries(final int lotteriesToCreate) {
		final LotteryGame lotteryGame = initRankingTest(lotteriesToCreate * 1000, 1);

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
	void testRankingPercent(final int purchaseAmount, final int expectedRank, final long prize) {
		final LotteryGame lotteryGame = initRankingTest(purchaseAmount, expectedRank);

		Map<Rank, Integer> rankResult = lotteryGame.makeWinner();
		double percent = lotteryGame.makeRankingPercent(rankResult);

		assertThat(percent).isEqualTo((double)(prize * (purchaseAmount / 1000)) / purchaseAmount);
	}

	private LotteryGame initRankingTest(final int purchaseAmount, final int expectedRank) {
		final PurchaseInformation purchaseInformation =
			createPurchaseInformation(purchaseAmount / 1000, purchaseAmount / 1000);
		final List<List<Integer>> rawManualLotteries =
			createRawManualLotteries(purchaseAmount / 1000, expectedRank);
		final ManualLotteryGeneratorStrategy manualLotteryGenerator =
			new ManualLotteryGeneratorStrategy(rawManualLotteries, new LotteryNumberFactory());
		final LotteryGenerateMock lotteryGenerateMock = new LotteryGenerateMock(expectedRank, purchaseAmount / 1000);
		final LotteryGame lotteryGame = new LotteryGame(purchaseInformation, lotteryGenerateMock,
			manualLotteryGenerator, new LotteryNumberFactory());
		lotteryGame.createWinningLottery(Arrays.asList(1, 2, 3, 4, 5, 6), 7);
		return lotteryGame;
	}

	private PurchaseInformation createPurchaseInformation(final int lotteriesToCreate,
			final int theNumberOfManualLotteries) {
		final PurchaseAmount purchaseAmount = new PurchaseAmount(lotteriesToCreate * 1000);
		return new PurchaseInformation(purchaseAmount, theNumberOfManualLotteries);
	}

	private List<List<Integer>> createRawManualLotteries(final int theNumberOfManualLotteries, final int rank) {
		final LotteryGenerateMockFactory lotteryGenerator = new LotteryGenerateMockFactory();
		final List<List<Integer>> lotteries = new ArrayList<>();
		for (int i = 0; i < theNumberOfManualLotteries; i++) {
			Lottery lottery = lotteryGenerator.of(rank);
			lotteries.add(Parser.toIntegerList(lottery.getNumbers()));
		}
		return lotteries;
	}
  
}
