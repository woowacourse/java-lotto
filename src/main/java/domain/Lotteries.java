package domain;

import java.util.ArrayList;
import java.util.List;

public class Lotteries {

	private final List<Lottery> lotteries;

	public Lotteries(final List<List<Integer>> lotteriesNumber) {
		lotteries = new ArrayList<>();
		lotteriesNumber.forEach(lotteryNumber ->
			lotteries.add(new Lottery(lotteryNumber)));
	}

	public List<Lottery> getLotteries() {
		return lotteries;
	}
}
