package view;

import domain.*;

import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {

	public static void printErrorMessage(String message) {
		System.out.println(message);
	}

	public static void printLottoTickets(LottoTickets lottoTickets) {
		System.out.println(lottoTickets.getLottoTicketsSize() + "개를 구매했습니다.");
		for (LottoTicket lottoTicket : lottoTickets.getLottoTickets()) {
			printLottoTicket(lottoTicket);
		}
	}

	private static void printLottoTicket(LottoTicket lottoTicket) {
		System.out.println(
			"[" +
				lottoTicket.getNumbers().getNumbers()
					.stream()
					.map(LottoNumber::getNumber)
					.map(String::valueOf)
					.collect(Collectors.joining(", "))
				+ "]");
	}

	public static void printResults(Result result) {
		System.out.println("\n당첨 통계");
		System.out.println("---------");

		printResult(result.getResults());
		printProfitRatio(result.getProfitRate());
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

	private static void printProfitRatio(float ratio) {
		System.out.println("총 수익률은 " + ratio + "입니다.(기준이 1이기 때문에 결과적으로 손해라는 의미임)");
	}

}
