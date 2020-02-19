package lotto.view;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import lotto.domain.Money;
import lotto.domain.Rank;

public class OutputView {
	public static void showEarningRate(Money money, List<Rank> ranks) {
		int sum = ranks.stream()
			.mapToInt(Rank::getReward)
			.sum();
		System.out.printf("총 수익률은 %.2f %%입니다.", sum / money.getMoney() * 100.0);
	}

	public static void showStatistics(List<Rank> ranks) {
		Map<Rank, Long> rankCounts = ranks.stream()
			.collect(Collectors.groupingBy(x -> x, Collectors.counting()));

		for (Rank rank : Rank.values()) {
			rankCounts.putIfAbsent(rank, 0L);
		}

		rankCounts.keySet().forEach(rank -> System.out.println(
			rank.getHitCount() + "개 일치 " + printBonus(rank) + rank.getReward() +
				"원 - " + rankCounts.get(rank) + "개"));
	}

	public static String printBonus(Rank rank) {
		if (rank == Rank.SECOND) {
			return "보너스 볼 일치 ";
		}

		return "";
	}

	public static void showPurchasedLottoCount(int purchasedLottoCount) {
		System.out.println(purchasedLottoCount + "개를 구매했습니다.");
	}
}
