package domain.generatestrategy;

import java.util.List;

@FunctionalInterface
public interface LotteryGenerateFamily {
	List<Integer> getNumbers();
}
