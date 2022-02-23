package domain.generateStrategy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import domain.generatestrategy.LotteryGenerateFamily;

public class LotteryGenerateMock implements LotteryGenerateFamily {

	private final List<List<Integer>> lotteriesNumber;
	private int index;

	public LotteryGenerateMock() {
		lotteriesNumber = new ArrayList<>();
		lotteriesNumber.add(Arrays.asList(1, 2, 3, 4, 5, 6));
		lotteriesNumber.add(Arrays.asList(1, 2, 3, 4, 5, 7));
		lotteriesNumber.add(Arrays.asList(1, 2, 3, 4, 5, 9));
		lotteriesNumber.add(Arrays.asList(1, 2, 3, 4, 9, 10));
		lotteriesNumber.add(Arrays.asList(1, 2, 3, 8, 9, 10));
		lotteriesNumber.add(Arrays.asList(1, 2, 10, 11, 12, 13));
		index = 0;
	}

	@Override
	public List<Integer> getNumbers() {
		if (index < lotteriesNumber.size()) {
			return lotteriesNumber.get(index++);
		}
		return Collections.emptyList();
	}
}
