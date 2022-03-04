package domain.generatestrategy;

import java.util.List;
import java.util.stream.Collectors;

import domain.Lottery;
import domain.LotteryNumber;

public class ManualLotteryGeneratorStrategy implements LotteryGenerateStrategy {

	private final List<Integer> rawManualLotteries;

	public ManualLotteryGeneratorStrategy(List<Integer> rawManualLotteries) {
		this.rawManualLotteries = rawManualLotteries;
	}

	@Override
	public Lottery getLottery() {
		List<LotteryNumber> manualLotteries = rawManualLotteries.stream()
			.map(LotteryNumber::new)
			.collect(Collectors.toList());
		return new Lottery(manualLotteries);
	}

}
