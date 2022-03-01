package domain.lottery;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class LotteryGenerator {

	private static final int MIN_LOTTERY_NUMBER = 1;
	private static final int MAX_LOTTERY_NUMBER = 45;

	private final List<Integer> numbers;

	public LotteryGenerator() {
		numbers = new ArrayList<>();
		for (int i = MIN_LOTTERY_NUMBER; i <= MAX_LOTTERY_NUMBER; i++) {
			numbers.add(i);
		}
	}

	public Lottery generateLottery(final List<Integer> lotteryNumbers) {
		Set<LotteryNumber> collect = lotteryNumbers.stream()
			.map(LotteryNumber::new)
			.collect(Collectors.toCollection(TreeSet::new));
		return Lottery.from(collect);
	}
}
