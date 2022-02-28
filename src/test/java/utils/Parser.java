package utils;

import java.util.List;
import java.util.stream.Collectors;

import domain.LotteryNumber;

public class Parser {

	public static List<LotteryNumber> toLotteryNumberList(final List<Integer> numbers) {
		return numbers.stream()
			.map(LotteryNumber::new)
			.collect(Collectors.toList());
	}
}
