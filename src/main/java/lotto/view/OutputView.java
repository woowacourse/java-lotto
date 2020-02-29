package lotto.view;

import lotto.domain.WinningPrize;
import lotto.dto.LottoDto;
import lotto.dto.LottoDtos;

import java.util.Map;

public class OutputView {
	public static void printWrongIntegerInput() {
		System.out.println("금액이 정수가 아닙니다. 다시 입력해주세요.");
	}

	public static void printLottoCount(int manualLottoCount, int autoLottoCount) {
		System.out.printf("수동으로 %d장, 자동으로 %d장을 구매했습니다.\n", manualLottoCount, autoLottoCount);
	}

	public static void printLottos(LottoDtos lottoDtos) {
		for (LottoDto lotto : lottoDtos.getLottoDtos()) {
			System.out.println(lotto.toString());
		}
	}

	public static void printWrongBonusNumberInput() {
		System.out.println("정수가 아닌 보너스 번호입니다.");
	}

	public static void printExceptionMessage(Exception e) {
		System.out.println(e.getMessage());
	}

	public static void printLottoResult(Map<WinningPrize, Integer> winningInformation) {
		winningInformation.forEach((winningPrize, amount) ->
				System.out.println(winningPrize.getDescription()
						+ "("
						+ winningPrize.getPrize() + "원): "
						+ amount + "개"));
	}

	public static void printEarningRate(double earningRate) {
		System.out.printf("총 수익률: %.2f%%", earningRate);
	}

	public static void printManualInputGuide() {
		System.out.println("수동으로 구매할 번호를 입력해 주세요.");
	}

	public static void printWinningNumberInputGuide() {
		System.out.println("지난 주 당첨 번호를 입력해 주세요.");
	}
}
