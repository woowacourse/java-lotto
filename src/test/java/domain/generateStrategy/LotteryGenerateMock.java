package domain.generateStrategy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import domain.Lottery;
import domain.generatestrategy.LotteryGenerateStrategy;
import utils.Parser;

public class LotteryGenerateMock implements LotteryGenerateStrategy {

	private final List<Lottery> lotteriesNumber;
	private final int rank;
	private int theNumberOfLotteries;

	public LotteryGenerateMock(final int rank, final int theNumberOfLotteries) {
		lotteriesNumber = new ArrayList<>();
		lotteriesNumber.add(toLottery(Arrays.asList(1, 2, 3, 4, 5, 6)));
		lotteriesNumber.add(toLottery(Arrays.asList(1, 2, 3, 4, 5, 7)));
		lotteriesNumber.add(toLottery(Arrays.asList(1, 2, 3, 4, 5, 9)));
		lotteriesNumber.add(toLottery(Arrays.asList(1, 2, 3, 4, 9, 10)));
		lotteriesNumber.add(toLottery(Arrays.asList(1, 2, 3, 8, 9, 10)));
		lotteriesNumber.add(toLottery(Arrays.asList(1, 2, 10, 11, 12, 13)));
		this.rank = rank - 1;
		this.theNumberOfLotteries = theNumberOfLotteries;
	}

	private Lottery toLottery(List<Integer> numbers) {
		return new Lottery(Parser.toLotteryNumberList(numbers));
	}

	@Override
	public Lottery getNumbers() {
		if (theNumberOfLotteries-- > 0) {
			return lotteriesNumber.get(rank);
		}

		return toLottery(Arrays.asList(0, 0, 0, 0, 0, 0));
	}
}
