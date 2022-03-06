package domain.lottery;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class LotteryGenerator {
	public static Lottery generateLottery(final List<Integer> numbers) {
		Set<LotteryNumber> lotteryNumbers = numbers.stream()
			.map(LotteryNumber::from)
			.collect(Collectors.toCollection(TreeSet::new));
		return Lottery.from(lotteryNumbers);
	}
}
