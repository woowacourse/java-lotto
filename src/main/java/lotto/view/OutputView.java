package lotto.view;

import java.util.List;
import java.util.Map;
import lotto.LottoDto;
import lotto.Rank;

public class OutputView {
    public void printLottos(List<LottoDto> lottos) {
        System.out.println(lottos.size() + "개를 구매했습니다.");
        for (LottoDto lotto : lottos) {
            System.out.println(lotto);
        }
    }

    public void printResult(Map<Rank, Integer> ranks) {
        System.out.println("당첨 통계 \n---------");
        for (Rank rank : Rank.values()) {
            if (rank == Rank.NO_PRIZE) {
                continue;
            }

            int winningCount = ranks.get(rank);
            int winningAmount = rank.getWinningAmount();
            int matchCount = rank.getMatchCount();
            boolean isBonusMatch = rank.isBonusMatch();
            if (isBonusMatch) {
                System.out.printf("%d개 일치, 보너스 볼 일치(%d원) - %d개\n", matchCount, winningAmount, winningCount);
                continue;
            }
            System.out.printf("%d개 일치 (%d원) - %d개\n", matchCount, winningAmount, winningCount);
        }
    }

    public void printWinningRatio(float ratio) {
        System.out.printf("총 수익률은 %.2f입니다.", ratio);
    }
}
