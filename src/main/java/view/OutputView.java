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

	private void printMessage(String message) {
		System.out.println(message);
	}

	private void printNewLine() {
		System.out.println();
	}

	public void printRequestMoney() {
		printMessage("구입금액을 입력해 주세요.");
	}

	public void printPurchasedLottoTicket(List<Lotto> lottoTicket) {
		printMessage(String.format("%d개를 구매했습니다.", lottoTicket.size()));
		for (Lotto lotto : lottoTicket) {
			printLottoNumber(sortLottoNumbers(lotto.getLotto()));
		}
	}

	private List<Number> sortLottoNumbers(List<Number> lotto) {
		lotto.sort(Comparator.comparingInt(Number::getNumber));
		return lotto;
	}

	private void printLottoNumber(List<Number> lottoNumbers) {
		int[] printingLottoNumbers = lottoNumbers.stream()
			.mapToInt(Number::getNumber)
			.toArray();

		printMessage(Arrays.toString(printingLottoNumbers));
	}

	public void printRequestWinningNumbers() {
		printNewLine();
		printMessage("지난 주 당첨 번호를 입력해 주세요.");
	}

	public void printRequestBonusNumber() {
		printMessage("보너스 볼을 입력해 주세요.");
	}

	public void printWinningResult(Map<LottoRank, Integer> winningResult) {
		printNewLine();
		printMessage("당첨 통계");
		printMessage("---------");

		Set<LottoRank> ranks = winningResult.keySet();
		for (LottoRank rank : ranks) {
			printMessage(createRankMessage(rank, winningResult.get(rank)));
		}
	}

	private String createRankMessage(LottoRank rank, int count) {
		if (rank == LottoRank.SECOND) {
			return String.format("%d개 일치, 보너스 볼 일치(%d원) - %d개", rank.getMatchCount(), rank.getAmount(), count);
		}
		return String.format("%d개 일치 (%d원) - %d개", rank.getMatchCount(), rank.getAmount(), count);
	}

	public void printRateOfProfit(double rateOfProfit) {
		printMessage(String.format("총 수익률은 %.2f입니다.", rateOfProfit));
	}
}
