package view;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import domain.Lotto;
import domain.LottoRank;
import domain.Number;

public class OutputView {

	private static final String REQUEST_MONEY_MESSAGE = "구입금액을 입력해 주세요.";
	private static final String NOTIFICATION_PURCHASED_LOTTO = "%d개를 구매했습니다.";
	private static final String REQUEST_WINNING_NUMBER_MESSAGE = "지난 주 당첨 번호를 입력해 주세요.";
	private static final String REQUEST_BONUS_NUMBER_MESSAGE = "보너스 볼을 입력해 주세요.";
	private static final String WINNING_RESULT_MESSAGE = "당첨 통계";
	private static final String DIVIDING_LINE = "---------";
	private static final String NOTIFICATION_WINNING_RANK_RESULT_WITH_BONUS_BALL = "%d개 일치, 보너스 볼 일치(%d원) - %d개";
	private static final String NOTIFICATION_WINNING_RANK_RESULT = "%d개 일치 (%d원) - %d개";
	private static final String NOTIFICATION_RATE_OF_PROFIT = "총 수익률은 %.2f입니다.";

	private void printNewLine() {
		System.out.println();
	}

	public void printRequestMoney() {
		System.out.println(REQUEST_MONEY_MESSAGE);
	}

	public void printPurchasedLottoTicket(final List<Lotto> lottoTicket) {
		System.out.println(String.format(NOTIFICATION_PURCHASED_LOTTO, lottoTicket.size()));
		for (Lotto lotto : lottoTicket) {
			printLottoNumber(sortLottoNumbers(lotto.getLotto()));
		}
	}

	private List<Number> sortLottoNumbers(final List<Number> lotto) {
		lotto.sort(Comparator.comparingInt(Number::getNumber));
		return lotto;
	}

	private void printLottoNumber(final List<Number> lottoNumbers) {
		int[] printingLottoNumbers = lottoNumbers.stream()
			.mapToInt(Number::getNumber)
			.toArray();

		System.out.println(Arrays.toString(printingLottoNumbers));
	}

	public void printRequestWinningNumbers() {
		printNewLine();
		System.out.println(REQUEST_WINNING_NUMBER_MESSAGE);
	}

	public void printRequestBonusNumber() {
		System.out.println(REQUEST_BONUS_NUMBER_MESSAGE);
	}

	public void printWinningResult(final Map<LottoRank, Integer> winningResult) {
		printNewLine();
		System.out.println(WINNING_RESULT_MESSAGE);
		System.out.println(DIVIDING_LINE);

		Set<LottoRank> ranks = winningResult.keySet();
		for (LottoRank rank : ranks) {
			System.out.println(createRankMessage(rank, winningResult.get(rank)));
		}
	}

	private String createRankMessage(final LottoRank rank, final int count) {
		if (rank == LottoRank.SECOND) {
			return String.format(NOTIFICATION_WINNING_RANK_RESULT_WITH_BONUS_BALL, rank.getMatchCount(), rank.getAmount(), count);
		}
		return String.format(NOTIFICATION_WINNING_RANK_RESULT, rank.getMatchCount(), rank.getAmount(), count);
	}

	public void printRateOfProfit(final double rateOfProfit) {
		System.out.println(String.format(NOTIFICATION_RATE_OF_PROFIT, rateOfProfit));
	}
}
