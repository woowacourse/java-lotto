package lotto.view;

import java.util.HashMap;
import java.util.List;

import lotto.domain.WinningPrize;
import lotto.dto.LottoDto;
import lotto.dto.LottoDtos;

public class OutputView {
	public static void printWrongMoneyInput() {
		System.out.println("금액이 정수가 아닙니다. 다시 입력해주세요.");
	}

	public static void printLottoCount(int lottoCount) {
		System.out.println(lottoCount + "개를 구매했습니다.");
	}

	public static void printLottos(LottoDtos lottoDtos) {
		for (LottoDto lotto : lottoDtos.getLottoDtos()) {
			System.out.println(lotto.getLottoNumber());
		}
	}

	public static void printWrongBonusNumberInput() {
		System.out.println("정수가 아닌 보너스 번호입니다.");
	}

	public static void printExceptionMessage(IllegalArgumentException e) {
		System.out.println(e.getMessage());
	}

	public static void printLottoResult(List<WinningPrize> winningPrizes) {
		HashMap<WinningPrize, Integer> winningInformation = makeWinningInformation(winningPrizes);
		for (WinningPrize winningPrize : WinningPrize.values()) {
			System.out.println(winningPrize.getPrizeName()
				+ "("
				+ winningPrize.getPrize() + "원): "
				+ winningInformation.get(winningPrize) + "개");
		}
		printEarningRate(winningInformation);
	}

	private static HashMap<WinningPrize, Integer> makeWinningInformation(List<WinningPrize> winningPrizes) {
		HashMap<WinningPrize, Integer> winningInformation = new HashMap<>();

		initializeWinningInformation(winningInformation);
		for (WinningPrize winningPrize : winningPrizes) {
			winningInformation.put(winningPrize, winningInformation.get(winningPrize) + 1);
		}
		return winningInformation;
	}

	private static void initializeWinningInformation(HashMap<WinningPrize, Integer> winningInformation) {
		for (WinningPrize winningPrize : WinningPrize.values()) {
			winningInformation.put(winningPrize, 0);
		}
	}

	private static void printEarningRate(HashMap<WinningPrize, Integer> winningInformation) {
		long totalEarning = 0;
		int totalCount = 0;

		for (WinningPrize winningPrize : WinningPrize.values()) {
			totalCount += winningInformation.get(winningPrize);
			totalEarning += (winningPrize.getPrize() * winningInformation.get(winningPrize));
		}
		System.out.println("총 수익률: " + totalEarning / (totalCount * 10) + "%");
	}
}
