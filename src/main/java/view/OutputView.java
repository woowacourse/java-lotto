package view;

import java.util.List;
import java.util.StringJoiner;

import domain.Lotto;
import domain.Money;
import domain.Profit;
import domain.Rank;

public class OutputView {
	public static void printLottos(List<Lotto> lottos) {
		System.out.println(String.format("%d개를 구매했습니다.", lottos.size()));
		for (Lotto lotto : lottos) {
			StringJoiner stringJoiner = new StringJoiner(", ", "[", "]");
			lotto.getNumbers()
				.forEach(lottoNumber -> stringJoiner.add(Integer.toString(lottoNumber.getNumber())));
			System.out.println(stringJoiner.toString());
		}
	}

	public static void printResult(List<Rank> ranks) {
		System.out.println("당첨 통계");
		System.out.println("-------");

		for (Rank rank : Rank.values()) {
			int matchedCount = rank.getMatchedCount();
			Money winningMoney = rank.getWinningMoney();
			int containingCount = rank.getContainingCount(ranks);
			System.out.println(
				String.format("%d개 일치 (%d원) - %d개", matchedCount, winningMoney.getMoney(), containingCount));
		}
	}

	public static void printProfit(Profit profit) {
		System.out.println(String.format("총 수익률은 %d%%입니다.", profit.getMoney()));
	}
}
