package lotto.view;

import lotto.domain.LottosResult;
import lotto.domain.Rank;

public class OutputView {
    public static void printPurchaseResult(int manualLottoQuantity, int autoLottoQuantity) {
        System.out.println(String.format("수동을 %d장, 자동으로 %d장 구매하였습니다.", manualLottoQuantity, autoLottoQuantity));
    }

    public static void printLottosResult(LottosResult result) {
        System.out.println("\n당첨 통계\n----------");

        for (Rank rank : Rank.values()) {
            System.out.println(String.format("%d개 일치%s (%d원) - %d개",
                    rank.getCountOfMatch(), printisMatchBonus(rank),
                    rank.getWinningMoney(), result.valueOf(rank)));
        }
    }

    private static String printisMatchBonus(Rank rank) {
        if (rank == rank.SECOND) {
            return ", 보너스 볼 일치";
        }
        return "";
    }

    public static void printROI(LottosResult result) {
        System.out.println(String.format("총 수익률은 %.2f%% 입니다.", result.getROI()));
    }
}
