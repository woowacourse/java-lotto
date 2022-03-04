package domain.generatestrategy;


import domain.Lottery;

@FunctionalInterface
public interface LotteryGenerateStrategy {
	Lottery getLottery();
}
