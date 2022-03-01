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
	private static final String REQUEST_WINNING_NUMBER_MESSAGE = "지난 주 당첨 번호를 입력해 주세요.";
	private static final String REQUEST_BONUS_NUMBER_MESSAGE = "보너스 볼을 입력해 주세요.";
	private static final String WINNING_RESULT_MESSAGE = "당첨 통계";
	private static final String DIVIDING_LINE = "---------";
	private static final String REQUEST_MANUAL_LOTTO_COUNT_MESSAGE = "수동으로 구매할 로또 수를 입력해 주세요.";
	private static final String REQUEST_MANUAL_LOTTO_MESSAGE = "수동으로 구매할 번호를 입력해 주세요.";

	private void printNewLine() {
		System.out.println();
	}

	public void printRequestMoney() {
		System.out.println(REQUEST_MONEY_MESSAGE);
	}

	public void printRequestManualLottoCount() {
		System.out.println(REQUEST_MANUAL_LOTTO_COUNT_MESSAGE);
	}

	public void printRequestManualLotto() {
		System.out.println(REQUEST_MANUAL_LOTTO_MESSAGE);
	}

	public void printPurchasedLottoTicket(int manualLottoCount, final List<Lotto> lottoTicket) {
		int autoLottoCount = lottoTicket.size() - manualLottoCount;
		System.out.println(
			String.format("수동으로 %d장, 자동으로 %d개를 구매했습니다.", manualLottoCount, autoLottoCount));
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
			return String.format("%d개 일치, 보너스 볼 일치(%d원) - %d개", rank.getMatchCount(),
				rank.getAmount(), count);
		}
		return String.format("%d개 일치 (%d원) - %d개", rank.getMatchCount(), rank.getAmount(), count);
	}

	public void printRateOfProfit(final double rateOfProfit) {
		System.out.println(String.format("총 수익률은 %.2f입니다.", rateOfProfit));
	}
}
