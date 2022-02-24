package domain.generatestrategy;

import java.util.List;

import domain.LotteryNumber;

@FunctionalInterface
public interface LotteryGenerateFamily {
	List<LotteryNumber> getNumbers();
}
