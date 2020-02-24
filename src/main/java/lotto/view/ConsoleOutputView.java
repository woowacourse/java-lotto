package lotto.view;

import lotto.domain.lotto.Lotto;
import lotto.domain.lotto.Lottos;
import lotto.domain.lottonumber.LottoNumber;
import lotto.domain.result.LottoRank;

import java.util.Map;
import java.util.stream.Collectors;

public class ConsoleOutputView {
	private static final String PURCHASE_COMPLETE_MESSAGE = "%d개를 구매했습니다.\n";
	private static final String DELIMITER = ",";
	private static final String STATISTICS_NOTICE_MESSAGE = "당첨 통계";
	private static final String SEPARATION_LINE = "---------";
	private static final String WINNING_RESULT = "%d개 일치 (%d원) - %d개";
	private static final String WINNING_SECOND_RANK_RESULT = "%d개 일치, 보너스 볼 일치(%d원) - %d개";
	private static final String WINNING_RATIO_MESSAGE = "총 수익률은 %d%%입니다.\n";

	private ConsoleOutputView() {
	}

	public static void printExceptionMessage(String exceptionMessage) {
		System.out.println(exceptionMessage);
	}

	public static void printPurchaseCompleteMessage(int numberOfLotto) {
		System.out.printf(PURCHASE_COMPLETE_MESSAGE, numberOfLotto);
	}

	public static void printPurchasedLotto(Lottos lottos) {
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
		System.out.println(STATISTICS_NOTICE_MESSAGE);
		System.out.println(SEPARATION_LINE);
	}

	public static void printWinningResult(Map<LottoRank, Integer> lottoRankCount) {
		lottoRankCount.entrySet().stream()
				.filter(entry -> !entry.getKey().isLottoRankOf(LottoRank.MISS))
				.map(entry -> getWinningResultMessage(entry.getKey(), entry.getValue()))
				.forEach(System.out::println);
	}

	private static String getWinningResultMessage(LottoRank lottoRank, int winningLottoCount) {
		String resultMessage = WINNING_RESULT;
		if (lottoRank.isLottoRankOf(LottoRank.SECOND)) {
			resultMessage = WINNING_SECOND_RANK_RESULT;
		}
		return String.format(resultMessage,
				lottoRank.getMatchCount(),
				lottoRank.getWinningMoney().getMoney(),
				winningLottoCount);
	}

	public static void printWinningRatio(int winningRatio) {
		System.out.printf(WINNING_RATIO_MESSAGE, winningRatio);
	}
}
