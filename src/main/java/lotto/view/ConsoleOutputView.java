package lotto.view;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import lotto.domain.LottoRank;
import lotto.domain.lotto.Lotto;
import lotto.domain.lotto.LottoNumber;
import lotto.domain.lotto.Lottos;

public class ConsoleOutputView {
	private static final String INPUT_MANUAL_LOTTO_MESSAGE = "수동으로 구매할 번호를 입력해주세요.";
	private static final String PURCHASE_COMPLETE_MESSAGE = "수동으로 %d장, 자동으로 %d개를 구매했습니다.\n";
	private static final String DELIMITER = ",";
	private static final String STATISTICS_NOTICE_MESSAGE = "당첨 통계";
	private static final String SEPARATION_LINE = "---------";
	private static final String WINNING_RESULT = "%d개 일치 (%d원) - %d개";
	private static final String WINNING_SECOND_RANK_RESULT = "%d개 일치, 보너스 볼 일치(%d원) - %d개";
	private static final String WINNING_RATIO_MESSAGE = "총 수익률은 %d%%입니다.\n";

	static void printExceptionMessage(String exceptionMessage) {
		System.out.println(exceptionMessage);
	}

	public static void printInputManualLottoMessage() {
		System.out.println(INPUT_MANUAL_LOTTO_MESSAGE);
	}

	public static void printPurchaseCompleteMessage(int manualLottoCount, int autoLottoCount) {
		System.out.printf(PURCHASE_COMPLETE_MESSAGE, manualLottoCount, autoLottoCount);
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

	public static void printWinningResult(Stream<Map.Entry<LottoRank, Integer>> result) {
		result.map(e -> getWinningResultMessage(e.getKey(), e.getValue())).forEach(System.out::println);
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