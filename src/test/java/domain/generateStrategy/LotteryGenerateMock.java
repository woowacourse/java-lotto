package domain.generateStrategy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import domain.Lottery;
import domain.generatestrategy.LotteryGenerateStrategy;
import utils.Parser;

public class LotteryGenerateMock implements LotteryGenerateStrategy {

	private final List<Lottery> lotteriesNumber;
	private int index;

	public LotteryGenerateMock() {
		lotteriesNumber = new ArrayList<>();
		lotteriesNumber.add(toLottery(Arrays.asList(1, 2, 3, 4, 5, 6)));
		lotteriesNumber.add(toLottery(Arrays.asList(1, 2, 3, 4, 5, 7)));
		lotteriesNumber.add(toLottery(Arrays.asList(1, 2, 3, 4, 5, 9)));
		lotteriesNumber.add(toLottery(Arrays.asList(1, 2, 3, 4, 9, 10)));
		lotteriesNumber.add(toLottery(Arrays.asList(1, 2, 3, 8, 9, 10)));
		lotteriesNumber.add(toLottery(Arrays.asList(1, 2, 10, 11, 12, 13)));
		index = 0;
	}

	private Lottery toLottery(List<Integer> numbers) {
		return new Lottery(Parser.toLotteryNumberList(numbers));
	}

	@Override
	public Lottery getNumbers() {
		if (index < lotteriesNumber.size()) {
			return lotteriesNumber.get(index++);
		}

		return toLottery(Arrays.asList(0, 0, 0, 0, 0, 0));
	}
}
