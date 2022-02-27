package domain.generateStrategy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import domain.generatestrategy.LotteryGenerateFamily;
import domain.lottery.LotteryNumber;
import domain.lottery.LotteryNumberGenerator;

public class LotteryGenerateMock implements LotteryGenerateFamily {

	private final List<Set<LotteryNumber>> lotteriesNumber;
	private int index;

	public LotteryGenerateMock() {
		lotteriesNumber = new ArrayList<>(new TreeSet<>());
		lotteriesNumber.add(LotteryNumberGenerator.generateLotteryNumbers(Arrays.asList(1, 2, 3, 4, 5, 6)));
		lotteriesNumber.add(LotteryNumberGenerator.generateLotteryNumbers(Arrays.asList(1, 2, 3, 4, 5, 7)));
		lotteriesNumber.add(LotteryNumberGenerator.generateLotteryNumbers(Arrays.asList(1, 2, 3, 4, 5, 9)));
		lotteriesNumber.add(LotteryNumberGenerator.generateLotteryNumbers(Arrays.asList(1, 2, 3, 4, 9, 10)));
		lotteriesNumber.add(LotteryNumberGenerator.generateLotteryNumbers(Arrays.asList(1, 2, 3, 8, 9, 10)));
		lotteriesNumber.add(LotteryNumberGenerator.generateLotteryNumbers(Arrays.asList(1, 2, 10, 11, 12, 13)));
		index = 0;
	}

	@Override
	public Set<LotteryNumber> getNumbers() {
		if (index < lotteriesNumber.size()) {
			return lotteriesNumber.get(index++);
		}
		return Collections.emptySet();
	}
}
