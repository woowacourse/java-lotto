package domain.factory;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import domain.Lottery;
import utils.Parser;

public class LotteryGenerateMockFactory {

	private static final Map<Integer, Lottery> cache = new HashMap<>();

	static {
		cache.put(1, toLottery(Arrays.asList(1, 2, 3, 4, 5, 6)));
		cache.put(2, toLottery(Arrays.asList(1, 2, 3, 4, 5, 7)));
		cache.put(3, toLottery(Arrays.asList(1, 2, 3, 4, 5, 9)));
		cache.put(4, toLottery(Arrays.asList(1, 2, 3, 4, 9, 10)));
		cache.put(5, toLottery(Arrays.asList(1, 2, 3, 8, 9, 10)));
		cache.put(6, toLottery(Arrays.asList(1, 2, 10, 11, 12, 13)));
	}

	private static Lottery toLottery(List<Integer> numbers) {
		return new Lottery(Parser.toLotteryNumberList(numbers));
	}

	public Lottery of(final int rank) {
		return cache.get(rank);
	}
}
