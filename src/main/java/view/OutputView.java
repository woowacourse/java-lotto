package view;

import domain.LottoNumber;
import domain.LottoTicket;
import domain.Result;
import domain.ResultStatics;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {
	private static final String START_ERROR = "[ERROR] ";

	private OutputView() {
	}

	public static void printErrorMessage(String message) {
		System.out.println(START_ERROR + message);
	}

	public static void printLottoTickets(int manualCount, List<LottoTicket> lottoTickets) {
		int randomCount = lottoTickets.size() - manualCount;
		System.out.println("\n수동으로 " + manualCount + "장, 자동으로 " + randomCount + "개를 구매했습니다.");
		for (LottoTicket lottoTicket : lottoTickets) {
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

	public static void printResult(Result result) {
		System.out.println("\n당첨 통계");
		System.out.println("---------");

		printResult(result.getResults());
		printProfitRate(result.getProfitRate());
	}

	private static void printResult(Map<ResultStatics, Integer> result) {
		result.keySet()
			.stream()
			.sorted(Comparator.comparing(ResultStatics::getPrice))
			.forEach(eachResult -> printEachResult(eachResult, result.get(eachResult)));
	}

	private static void printEachResult(ResultStatics result, int count) {
		if (result == ResultStatics.NOTHING) {
			return;
		}

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
