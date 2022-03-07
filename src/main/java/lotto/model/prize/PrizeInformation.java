package lotto.model.prize;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;
import java.util.Set;

import lotto.model.Money;
import lotto.model.lotto.Lottos;
import lotto.model.lotto.WinningBalls;

public class PrizeInformation {
	private final EnumMap<Prize, Integer> prizeInformation;

	private PrizeInformation(EnumMap<Prize, Integer> prizeInformation) {
		this.prizeInformation = prizeInformation;
	}

	public static PrizeInformation from(Lottos lottos, WinningBalls winningBalls) {
		EnumMap<Prize, Integer> prizeInformation = new EnumMap<>(Prize.class);
		initializeInformation(prizeInformation);

		List<Prize> prizes = getPrizes(lottos, winningBalls);
		addCount(prizeInformation, prizes);

		return new PrizeInformation(prizeInformation);
	}

	private static void initializeInformation(EnumMap<Prize, Integer> prizeInformation) {
		Arrays.stream(Prize.values())
			.filter(prize -> prize != Prize.NONE)
			.forEach(prize -> prizeInformation.put(prize, 0));
	}

	private static List<Prize> getPrizes(Lottos lottos, WinningBalls winningBalls) {
		return lottos.match(winningBalls);
	}

	private static void addCount(EnumMap<Prize, Integer> prizeInformation, List<Prize> prizes) {
		prizes.stream()
			.filter(prize -> prize != Prize.NONE)
			.forEach(prize -> addCount(prizeInformation, prize));
	}

	private static void addCount(EnumMap<Prize, Integer> prizeInformation, Prize prize) {
		prizeInformation.merge(prize, 1, Integer::sum);
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
