package domain.generatestrategy;


import domain.Lottery;

@FunctionalInterface
public interface LotteryGenerateFamily {
	Lottery getNumbers();
}
