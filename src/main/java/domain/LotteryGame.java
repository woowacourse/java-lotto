package domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import domain.factory.LotteryNumberFactory;
import domain.generatestrategy.LotteryGenerateStrategy;

public class LotteryGame {

	private final PurchaseInformation purchaseInformation;
	private final LotteryGenerateStrategy autoLotteryGenerator;
	private final LotteryGenerateStrategy manualLotteryGenerator;
	private final LotteryNumberFactory lotteryNumberFactory;

	private Lotteries lotteries;
	private WinningLottery winningLottery;

	public LotteryGame(final PurchaseInformation purchaseInformation, final LotteryGenerateStrategy autoLotteryGenerator,
			final LotteryGenerateStrategy manualLotteryGenerator, final LotteryNumberFactory lotteryNumberFactory) {
		this.purchaseInformation = purchaseInformation;
		this.autoLotteryGenerator = autoLotteryGenerator;
		this.manualLotteryGenerator = manualLotteryGenerator;
		this.lotteryNumberFactory = lotteryNumberFactory;
		createLottery();
	}

	private void createLottery() {
		final List<Lottery> lotteryNumbers = Stream.of(
				 generateLotteries(autoLotteryGenerator, purchaseInformation.getTheNumberOfAutoPurchasedLotteries()),
				generateLotteries(manualLotteryGenerator, purchaseInformation.getTheNumberOfManualLotteries())
			)
			.flatMap(Collection::stream)
			.collect(Collectors.toList());
		lotteries = new Lotteries(lotteryNumbers);
	}

	private List<Lottery> generateLotteries(final LotteryGenerateStrategy lotteryGenerator, final int theNumberOfLottery) {
		final List<Lottery> lotteries = new ArrayList<>();
		for (int i = 0; i < theNumberOfLottery; i++) {
			lotteries.add(lotteryGenerator.getLottery());
		}
		return lotteries;
	}

	public void createWinningLottery(final List<Integer> winningNumbers, final Integer bonusBall) {
		final List<LotteryNumber> winningLotteryNumbers = winningNumbers.stream()
			.map(lotteryNumberFactory::of)
			.collect(Collectors.toList());
		winningLottery = new WinningLottery(winningLotteryNumbers, lotteryNumberFactory.of(bonusBall));
	}

	public Map<Rank, Integer> makeWinner() {
		return lotteries.getTheNumberOfWinners(winningLottery);
	}

	public double makeRankingPercent(final Map<Rank, Integer> rankResult) {
		long earningAmount = 0;
		for (Rank rank : rankResult.keySet()) {
			earningAmount += rankResult.get(rank) * rank.getPrize();
		}
		return purchaseInformation.calculateEarningRate(earningAmount);
	}

	public List<Lottery> getLotteries() {
		return lotteries.getLotteries();
	}
}
