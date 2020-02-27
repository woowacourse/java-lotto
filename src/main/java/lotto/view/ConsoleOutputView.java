package lotto.view;

import lotto.domain.result.LottoRank;
import lotto.domain.result.WinningResult;
import lotto.domain.lotto.LottoMoney;
import lotto.domain.lotto.Lottos;

public class ConsoleOutputView {
	private static final String INPUT_MANUAL_LOTTO_MESSAGE = "수동으로 구매할 번호를 입력해주세요.";
	private static final String PURCHASE_COMPLETE_MESSAGE = "수동으로 %d장, 자동으로 %d개를 구매했습니다.\n";
	private static final String STATISTICS_NOTICE_MESSAGE = "당첨 통계";
	private static final String SEPARATION_LINE = "---------";
	private static final String WINNING_RESULT = "%d개 일치 (%d원) - %d개";
	private static final String WINNING_SECOND_RANK_RESULT = "%d개 일치, 보너스 볼 일치(%d원) - %d개";
	private static final String WINNING_RATIO_MESSAGE = "총 수익률은 %d%%입니다.\n";

	private ConsoleOutputView() {
	}

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
		lottos.forEach(System.out::println);
	}

	public static void printStatisticsMessage() {
		System.out.println(STATISTICS_NOTICE_MESSAGE);
		System.out.println(SEPARATION_LINE);
	}

	public static void printWinningResult(WinningResult result) {
		result.exceptMiss().forEach(e -> System.out.println(getWinningResultMessage(e.getKey(), e.getValue())));
	}

	private static String getWinningResultMessage(LottoRank lottoRank, int winningLottoCount) {
		String resultMessage = WINNING_RESULT;
		if (lottoRank.isLottoRankOf(LottoRank.SECOND)) {
			resultMessage = WINNING_SECOND_RANK_RESULT;
		}
		return String.format(resultMessage, lottoRank.getMatchCount(), lottoRank.getWinningMoney(), winningLottoCount);
	}

	public static void printWinningRatio(WinningResult result, LottoMoney lottoMoney) {
		System.out.printf(WINNING_RATIO_MESSAGE, result.getWinningRatio(lottoMoney));
	}
}