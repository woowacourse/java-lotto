package lotto.view;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import lotto.domain.Lotto;
import lotto.domain.LottoNumber;
import lotto.domain.LottoRank;

public class ConsoleOutputView {
	public static final String PURCHASE_COMPLETE_MESSAGE = "%d개를 구매했습니다.\n";
	public static final String DELIMITER = ",";
	public static final String STATISTICS_MESSAGE_1 = "당첨 통계";
	public static final String STATISTICS_MESSAGE_2 = "---------";
	public static final String STATISTICS_RESULT = "%d개 일치 (%d원) - %d개\n";
	public static final String STATISTICS_SECOND_RANK_RESULT = "%d개 일치, 보너스 볼 일치(%d원) - %d개\n";

	public static void printExceptionMessage(String exceptionMessage) {
		System.out.println(exceptionMessage);
	}

	public static void printPurchaseCompleteMessage(int numberOfLotto) {
		System.out.printf(PURCHASE_COMPLETE_MESSAGE, numberOfLotto);
	}

	public static void printPurchasedLotto(List<Lotto> lottos) {
		for (Lotto lotto : lottos) {
			String lottoNumber = lotto.getLottoNumbers().stream()
				.map(LottoNumber::getNumber)
				.map(Object::toString)
				.collect(Collectors.joining(DELIMITER));
			System.out.println(wrapSquareBracket(lottoNumber));
		}
	}

	private static String wrapSquareBracket(String lottoNumber) {
		return "[" + lottoNumber + "]";
	}

	public static void printStatisticsMessage() {
		System.out.println(STATISTICS_MESSAGE_1);
		System.out.println(STATISTICS_MESSAGE_2);
	}

	public static void printStatisticsResult(Map<LottoRank, Integer> lottoRankCount) {
		for (Map.Entry<LottoRank, Integer> lottoRankEntry : lottoRankCount.entrySet()) {
			if (lottoRankEntry.getKey().isLottoRankOf(LottoRank.SECOND)) {
				printSecondRankResult(lottoRankEntry);
				continue;
			}
			if (lottoRankEntry.getKey().isLottoRankOf(LottoRank.MISS)) {
				continue;
			}
			printRankResult(lottoRankEntry);
		}
	}

	private static void printRankResult(Map.Entry<LottoRank, Integer> lottoRankEntry) {
		System.out.printf(STATISTICS_RESULT, lottoRankEntry.getKey().getMatchCount(),
			lottoRankEntry.getKey().getWinningMoney().getMoney(),
			lottoRankEntry.getValue());
	}

	private static void printSecondRankResult(Map.Entry<LottoRank, Integer> lottoRankEntry) {
		System.out.printf(STATISTICS_SECOND_RANK_RESULT, lottoRankEntry.getKey().getMatchCount(),
			lottoRankEntry.getKey().getWinningMoney().getMoney(),
			lottoRankEntry.getValue());
	}

}
