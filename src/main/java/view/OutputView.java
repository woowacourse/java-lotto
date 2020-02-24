package view;

import java.util.List;
import java.util.StringJoiner;

import domain.GameResult;
import domain.Lotto;
import domain.LottoGame;
import domain.Money;
import domain.Rank;

public class OutputView {

	private static final String DELIMITER = ", ";
	private static final String PREFIX = "[";
	private static final String SUFFIX = "]";

	public static void printLottos(LottoGame lottoGame) {
		List<Lotto> lottos = lottoGame.getLottos();
		System.out.println(String.format("%d개를 구매했습니다.", lottos.size()));
		for (Lotto lotto : lottos) {
			StringJoiner stringJoiner = new StringJoiner(DELIMITER, PREFIX, SUFFIX);
			lotto.getNumbers()
				.forEach(lottoNumber -> stringJoiner.add(Integer.toString(lottoNumber.getNumber())));
			System.out.println(stringJoiner.toString());
		}
	}

	public static void printErrorMessage(String message) {
		System.out.println(message);
	}

	public static void printStatistics(GameResult gameResult, Money purchaseMoney) {
		System.out.println("당첨 통계");
		System.out.println("-------");

		for (Rank rank : Rank.values()) {
			int matchedCount = rank.getMatchedCount();
			Money winningMoney = rank.getWinningMoney();
			int containingCount = gameResult.numberOfRank(rank);
			System.out.println(
				String.format("%d개 일치 (%.0f원) - %d개", matchedCount, winningMoney.getMoney(), containingCount));
		}

		System.out.println(String.format("총 수익률은 %.0f%%입니다.", gameResult.calculateProfit(purchaseMoney)));
	}
}
