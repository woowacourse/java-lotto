package domain.generatestrategy;

import java.util.Set;

import domain.lottery.LotteryNumber;

public interface LotteryGenerateFamily {
	Set<LotteryNumber> getNumbers();
}
