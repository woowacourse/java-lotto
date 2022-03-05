package domain.factory;

import static java.util.stream.Collectors.*;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.IntStream;

import domain.LotteryNumber;

public class LotteryNumberFactory {

	private static final Map<Integer, LotteryNumber> cache;

	static {
		cache = IntStream.rangeClosed(LotteryNumber.NUMBER_MIN_RANGE, LotteryNumber.NUMBER_MAX_RANGE)
			.mapToObj(LotteryNumber::new)
			.collect(toMap(LotteryNumber::getLotteryNumber, Function.identity()));
	}

	public LotteryNumber of(final int number) {
		LotteryNumber.checkNumberRange(number);
		return cache.get(number);
	}

}
