package lotto.view;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import lotto.domain.Lotto;
import lotto.domain.Money;
import lotto.domain.Rank;

public class OutputView {
	private static final int PERCENTAGE_MULTIPLE = 100;
	private static final String EMPTY_STRING = "";
	private static final String EARNING_RATE_MESSAGE = "총 수익률은 %d %%입니다.";
	private static final String SECOND_RANK_ADDITIONAL_MESSAGE = "보너스 볼 일치 ";
	private static final String PURCHASED_LOTTO_MESSAGE = "개를 구매했습니다.";
	private static final String NEW_LINE = "\n";
	private static final String STATISTICS_FORMAT = "%d개 일치 %s%s원 - %d개%s";

	public static void showEarningRate(Money boughtLottoMoney, List<Rank> ranks) {
		int earningRate = ranks.stream()
			.filter(Objects::nonNull)
			.map(Rank::getReward)
			.reduce(Money::sum)
			.map(sum -> sum.multiple(PERCENTAGE_MULTIPLE))
			.map(sum -> sum.getQuotient(boughtLottoMoney))
			.orElse(0);

		System.out.printf(EARNING_RATE_MESSAGE, earningRate);
	}

	public static void showStatistics(List<Rank> ranks) {
		Map<Rank, Long> rankCounts = ranks.stream()
			.filter(Objects::nonNull)
			.collect(Collectors.groupingBy(x -> x, Collectors.counting()));

		for (Rank rank : Rank.values()) {
			rankCounts.putIfAbsent(rank, 0L);
		}

		rankCounts.keySet().stream().sorted().forEach(rank ->
			System.out.printf(STATISTICS_FORMAT, rank.getHitCount(), printBonus(rank), rank.getReward(),
				rankCounts.get(rank), NEW_LINE));
	}

	public static String printBonus(Rank rank) {
		if (rank == Rank.SECOND) {
			return SECOND_RANK_ADDITIONAL_MESSAGE;
		}

		return EMPTY_STRING;
	}

	public static void showPurchasedLottoCount(int purchasedLottoCount) {
		System.out.println(purchasedLottoCount + PURCHASED_LOTTO_MESSAGE);
	}

	public static void showPurchasedLottoNumbers(List<Lotto> purchasedLottoNumbers) {
		System.out.println(purchasedLottoNumbers.stream()
			.map(x -> String.valueOf(x.getNumbers()))
			.collect(Collectors.joining(NEW_LINE))
		);
	}

	public static void showResult(Money money, List<Rank> ranks) {
		showStatistics(ranks);
		showEarningRate(money, ranks);
	}
}
