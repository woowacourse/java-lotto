package lotto.model.prize;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;
import java.util.Set;

import lotto.model.Money;

public class PrizeInformation {
	private final EnumMap<Prize, Integer> prizeInformation;

	private PrizeInformation(EnumMap<Prize, Integer> prizeInformation) {
		this.prizeInformation = prizeInformation;
	}

	public static PrizeInformation from(List<MatchResult> matchResults) {
		EnumMap<Prize, Integer> prizeInformation = new EnumMap<>(Prize.class);
		initializeInformation(prizeInformation);
		getPrizeCount(prizeInformation, matchResults);

		return new PrizeInformation(prizeInformation);
	}

	private static void initializeInformation(EnumMap<Prize, Integer> prizeInformation) {
		Arrays.stream(Prize.values())
			.filter(prize -> prize != Prize.NONE)
			.forEach(prize -> prizeInformation.put(prize, 0));
	}

	private static void getPrizeCount(EnumMap<Prize, Integer> prizeInformation, List<MatchResult> matchResults) {
		matchResults.stream()
			.map(Prize::getPrize)
			.filter(prize -> prize != Prize.NONE)
			.forEach(prize -> addCount(prizeInformation, prize));
	}

	private static void addCount(EnumMap<Prize, Integer> prizeInformation, Prize prize) {
		prizeInformation.replace(prize, prizeInformation.get(prize) + 1);
	}

	public double calculateEarningRate(Money money) {
		return money.rate(getTotalAmount());
	}

	private int getTotalAmount() {
		return prizeInformation.keySet().stream()
				.mapToInt(this::getAmount)
				.sum();
	}

	private int getAmount(Prize prize) {
		return prize.pickAmount(prizeInformation.get(prize));
	}

	public Set<Prize> getKeys() {
		return prizeInformation.keySet();
	}

	public int getCount(Prize prize) {
		return prizeInformation.get(prize);
	}
}
