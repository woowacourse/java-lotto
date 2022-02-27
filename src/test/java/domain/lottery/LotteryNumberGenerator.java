package domain.lottery;

import java.util.List;
import java.util.stream.Collectors;

public class LotteryNumberGenerator {
	public static List<LotteryNumber> generateLotteryNumbers(final List<Integer> numbers) {
		return numbers.stream()
			.map(LotteryNumber::new)
			.collect(Collectors.toList());
	}
}
