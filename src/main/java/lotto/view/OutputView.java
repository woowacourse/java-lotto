package lotto.view;

import lotto.Lotto;
import lotto.Prize;
import lotto.WinningStatistics;

import java.util.List;

public class OutputView {
    private OutputView() {}

    public static void printLottos(List<Lotto> lottos) {
        System.out.println("%d개를 구매했습니다.".formatted(lottos.size()));
        for (Lotto lotto : lottos) {
            System.out.println(lotto.getNumbers());
        }
    }

    public static void printWinningStatistics(WinningStatistics winningStatistics, double returnRate) {
        System.out.println("\n당첨 통계");
        System.out.println("---------");

        for (Prize prize : Prize.values()) {
            printDetail(prize, winningStatistics.getPrizeCount(prize));
        }

        printReturnRate(returnRate);
    }

    private static void printReturnRate(double returnRate) {
        System.out.print(String.format("총 수익률은 %.2f입니다.", returnRate));
        if (returnRate < 1) {
            System.out.println("(기준이 1이기 때문에 결과적으로 손해라는 의미임)");
        }
    }

    private static void printDetail(Prize prize, int getPrizeCount) {
        if (prize == Prize.NONE) {
            return;
        }

        if (prize == Prize.SECOND) {
            System.out.println(String.format("%d개 일치, 보너스 볼 일치(%d원) - %d개",
                    prize.getMatchCount(), prize.getWinningAmount(), getPrizeCount));
            return;
        }

        System.out.println(String.format("%d개 일치 (%d원) - %d개",
                prize.getMatchCount(), prize.getWinningAmount(), getPrizeCount));
    }

    public static void printErrorMessage(String errorMessage) {
        System.out.println(errorMessage);
    }
}
