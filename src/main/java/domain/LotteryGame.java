package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
		lotteries = new Lotteries();
		createManualLottery();
		createAutoLottery();
	}

	private void createManualLottery() {
		final int theNumberOfManualLottery = purchaseInformation.getTheNumberOfManualLotteries();
		final List<Lottery> manualLottery = new ArrayList<>();
		for (int i = 0; i < theNumberOfManualLottery; i++) {
			manualLottery.add(manualLotteryGenerator.getLottery());
		}
		lotteries.addLotteries(manualLottery);
	}

	private void createAutoLottery() {
		final List<Lottery> createdLotteries = new ArrayList<>();
		for (int i = 0; i < purchaseInformation.getTheNumberOfAutoPurchasedLotteries(); i++) {
			createdLotteries.add(autoLotteryGenerator.getLottery());
		}
		lotteries.addLotteries(createdLotteries);
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
