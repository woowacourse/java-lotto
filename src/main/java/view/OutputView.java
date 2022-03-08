package view;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import controller.dto.BuyingInfoDto;
import controller.dto.LottoDto;
import controller.dto.LottoResultDto;
import controller.dto.RankDto;
import domain.Rank;

public class OutputView {
	private static final String ERROR_MESSAGE = "[ERROR] ";

	private OutputView() {
	}

	public static void printGuideMessage(String message) {
		System.out.println(message);
	}

	public static void printErrorMessage(String errorMessage) {
		System.out.println(ERROR_MESSAGE + errorMessage);
	}

	public static void printLottoCount(BuyingInfoDto infoDto) {
		System.out.println(
			String.format("수동으로 %d장, 자동으로 %d개를 구매했습니다.",
				infoDto.getManualLottoCount(), infoDto.getAutoLottoCount()));
	}

	public static void printLottos(List<LottoDto> lottos) {
		lottos.stream()
			.forEach(OutputView::printLotto);
		System.out.println();
	}

	private static void printLotto(LottoDto lotto) {
		String format = "[%s]";
		System.out.println(String.format(format, lotto.getLotto().stream()
			.map(String::valueOf)
			.collect(Collectors.joining(", "))));
	}

	public static void printLottoResult(List<RankDto> ranks) {
		System.out.println("\n당첨 통계\n---------");
		ranks.forEach(OutputView::printRankCount);
	}

	private static void printRankCount(RankDto rankDto) {
		String format = "%d개 일치 (%d원)- %d개";
		if (rankDto.isBonus()) {
			format = "%d개 일치, 보너스 볼 일치(%d원)- %d개";
		}
		System.out.println(String.format(format, rankDto.getMatch(), rankDto.getMoney(), rankDto.getAmount()));
	}

	public static void printProfitRate(double profitRate) {
		String format = "총 수익률은 %.2f입니다.";
		System.out.println(String.format(format, profitRate));
	}
}
