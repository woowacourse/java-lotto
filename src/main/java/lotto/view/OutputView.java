package lotto.view;

import java.util.List;
import java.util.stream.Collectors;

import lotto.domain.Lotto;
import lotto.domain.LottoNumber;
import lotto.domain.LottoRank;
import lotto.domain.LottoStatistics;
import lotto.domain.Lottos;

public class OutputView {
	public static void printBuyCount(int manualBuyCount, int autoBuyCount) {
		System.out.printf("수동으로 %d장, 자동으로 %d개를 구매했습니다.\n", manualBuyCount, autoBuyCount);
	}

	public static void printLottos(Lottos lottos) {
		lottos.forEach(OutputView::printLotto);
	}

	private static void printLotto(Lotto lotto) {
		List<Integer> lottoNumbers = lotto.getValue()
				.stream()
				.map(LottoNumber::getValue)
				.collect(Collectors.toList());
		System.out.println(lottoNumbers);
	}

	public static void printStatistics(LottoStatistics lottoStatistics) {
		System.out.println("당첨 통계");
		System.out.println("---------");
		lottoStatistics.getLottoRanksCount()
				.forEach(OutputView::printRankCount);
		System.out.println("총 수익률은 " + lottoStatistics.getProfitRate() + "%입니다.");
	}

	private static void printRankCount(LottoRank rank, Long count) {
		if (rank == LottoRank.SECOND) {
			System.out.printf("%d 개 일치 보너스 볼 일치(%d원) - %d개\n", rank.getMatchCount(), rank.getWinnings(), count);
			return;
		}
		System.out.printf("%d 개 일치(%d원) - %d개\n", rank.getMatchCount(), rank.getWinnings(), count);
	}
}
