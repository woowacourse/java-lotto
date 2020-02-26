package lotto.view;

import lotto.domain.WinningPrize;
import lotto.dto.LottoCountDto;
import lotto.dto.LottoDto;
import lotto.dto.LottoResultDto;
import lotto.dto.LottosDto;

public class OutputView {
	public static void printWrongMoneyInput(Exception e) {
		System.out.println(e.getMessage());
		System.out.println("금액이 정수가 아닙니다. 다시 입력해주세요.");
	}

	public static void printLottoCount(LottoCountDto lottoCountDto) {
		System.out.println(String.format("수동으로 %d장, 자동으로 %d장을 구매했습니다.", lottoCountDto.getManualLottoCount(),
			lottoCountDto.getAutoLottoCount()));
	}

	public static void printLottos(LottosDto lottosDto) {
		for (LottoDto lotto : lottosDto.getLottosDto()) {
			System.out.println(lotto.getLottoNumbers().getLottoNumbers());
		}
	}

	public static void printWrongBonusNumberInput(Exception e) {
		System.out.println(e.getMessage());
		System.out.println("정수가 아닌 보너스 번호입니다.");
	}

	public static void printExceptionMessage(Exception e) {
		System.out.println(e.getMessage());
	}

	public static void printLottoResult(LottoResultDto lottoResultDto) {
		for (WinningPrize winningPrize : WinningPrize.values()) {
			System.out.println(
				String.format("%s(%,d원): %d개", winningPrize.getPrizeDescription(), winningPrize.getPrize(),
					lottoResultDto.getWinnerCountMapper().get(winningPrize)));
		}
		System.out.println(String.format("총 수익률: %,d%%", lottoResultDto.getEarningRate()));
	}

	public static void printWrongManualLottoCount(Exception e) {
		System.out.println(e.getMessage());
		System.out.println("정수가 아닌 수동 로또 수입니다.");
	}
}
