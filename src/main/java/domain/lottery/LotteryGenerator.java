package domain.lottery;

import static domain.lottery.LotteryNumber.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class LotteryGenerator {

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
