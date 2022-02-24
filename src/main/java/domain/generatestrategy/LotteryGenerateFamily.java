package domain.generatestrategy;

import java.util.List;

import domain.lottery.LotteryNumber;

public interface LotteryGenerateFamily {
	List<LotteryNumber> getNumbers();
}
