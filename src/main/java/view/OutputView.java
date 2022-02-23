package view;

import domain.Lotto;
import domain.Lottos;

import domain.ResultStatics;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {

	public static void printLottos(Lottos lottos) {
		System.out.println(lottos.getLottosSize() + "개를 구매했습니다.");
		for (Lotto lotto : lottos.getLottos()) {
			printLotto(lotto);
		}
	}

	private static void printLotto(Lotto lotto) {
		System.out.println(
			"[" + lotto.getNumbers().stream().map(String::valueOf).collect(Collectors.joining(", ")) + "]");
	}

	public static void printStatistics(Map<ResultStatics, Integer> results) {
		System.out.println("\n당첨 통계");
		System.out.println("---------");
		for (ResultStatics eachResult : results.keySet()) {
			printEachResult(eachResult, results.get(eachResult));
		}
	}

	private static void printEachResult(ResultStatics result, int count) {
		String bonusDisplay = " ";
		if (result.getPrice() == 0) {
			return;
		}
		if (result.isHitBonus()) {
			bonusDisplay = ", 보너스 볼 일치";
		}
		System.out.println(result.getNumberMatches() + "개 일치" + bonusDisplay + "(" + result.getPrice() + "원) - " + count + "개");
	}

	public static void printProfitRatio(float ratio) {
		System.out.println("총 수익률은 " + ratio + "입니다.(기준이 1이기 때문에 결과적으로 손해라는 의미임)");
	}

	public static void printErrorMessage(String message) {
		System.out.println(message);
	}
}
