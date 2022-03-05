package domain.lottery;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class LotteryGenerator {
	public static Lottery generateLottery(final List<Integer> lotteryNumbers) {
		Set<LotteryNumber> collect = lotteryNumbers.stream()
			.map(LotteryNumber::new)
			.collect(Collectors.toCollection(TreeSet::new));
		return Lottery.from(collect);
	}
}
