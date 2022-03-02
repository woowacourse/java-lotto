package view;

import domain.*;

import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {

	private static final String START_ERROR = "[ERROR] ";

	private OutputView() {
	}

	public static void printErrorMessage(String message) {
		System.out.println(START_ERROR + message);
	}

	public static void printLottoTickets(int manualCount, int randomCount, LottoTickets lottoTickets) {
		System.out.println("\n수동으로 " + manualCount + "장, 자동으로 " + randomCount + "개를 구매했습니다.");
		for (LottoTicket lottoTicket : lottoTickets.getLottoTickets()) {
			printLottoTicket(lottoTicket);
		}
	}

	private static void printLottoTicket(LottoTicket lottoTicket) {
		System.out.println("[" +
			lottoTicket.getNumbers()
				.stream()
				.map(LottoNumber::getNumber)
				.map(String::valueOf)
				.collect(Collectors.joining(", "))
			+ "]");
	}

	public static void printResults(Result result, float profitRate) {
		System.out.println("\n당첨 통계");
		System.out.println("---------");

		printResult(result.getResults());
		printProfitRate(profitRate);
	}

	private static void printResult(Map<ResultStatics, Integer> result) {
		for (ResultStatics resultStatics : result.keySet()) {
			if (resultStatics == ResultStatics.NOTHING) {
				continue;
			}
			printEachResult(resultStatics, result.get(resultStatics));
		}
	}

	private static void printEachResult(ResultStatics result, int count) {
		String bonusDisplay = " ";
		if (result.isHitBonus()) {
			bonusDisplay = ", 보너스 볼 일치";
		}
		System.out.println(result.getNumberMatches() + "개 일치" + bonusDisplay + "(" + result.getPrice() + "원) - " + count + "개");
	}

	private static void printProfitRate(float profitRate) {
		String profitRateString = String.format("%.2f", profitRate);
		System.out.println(("총 수익률은 " + profitRateString + "입니다."));
	}

}
