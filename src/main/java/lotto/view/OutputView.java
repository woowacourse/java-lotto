package lotto.view;

import lotto.domain.Lotto;
import lotto.domain.Rank;
import lotto.domain.WinningStatistics;

import java.util.List;

public class OutputView {
    private static final int STANDARD_RATE = 1;

    private OutputView() {}

    public static void printLottos(final List<Lotto> lottos) {
        System.out.printf("%d개를 구매했습니다.%n", lottos.size());
        for (final Lotto lotto : lottos) {
            System.out.println(lotto.getNumbers());
        }
    }

    public static void printWinningStatistics(final WinningStatistics winningStatistics, final double returnRate) {
        System.out.println("\n당첨 통계");
        System.out.println("---------");

        for (final Rank rank : Rank.values()) {
            printDetail(rank, winningStatistics.getRankCount(rank));
        }

        printReturnRate(returnRate);
    }

    private static void printReturnRate(final double returnRate) {
        System.out.printf("총 수익률은 %.2f입니다.", returnRate);
        if (returnRate < STANDARD_RATE) {
            System.out.println("(기준이 %d이기 때문에 결과적으로 손해라는 의미임)".formatted(STANDARD_RATE));
        }
    }

    private static void printDetail(final Rank rank, final int rankCount) {
        if (rank == Rank.NONE) {
            return;
        }

        if (rank == Rank.SECOND) {
            System.out.printf("%d개 일치, 보너스 볼 일치(%d원) - %d개%n",
                    rank.getMatchCount(), rank.getWinningAmount(), rankCount);
            return;
        }

        System.out.printf("%d개 일치 (%d원) - %d개%n",
                rank.getMatchCount(), rank.getWinningAmount(), rankCount);
    }

    public static void printErrorMessage(final String errorMessage) {
        System.out.println(errorMessage);
    }
}
