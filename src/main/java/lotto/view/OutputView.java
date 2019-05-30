package lotto.view;

import lotto.domain.*;

public class OutputView {
    private OutputView() {

    }

    public static void showBuyCounts(final int manualPurchaseCount, final int autoPurchaseCount) {
        System.out.printf("수동으로 %d장, 자동으로 %d개를 구매했습니다.\n", manualPurchaseCount, autoPurchaseCount);
    }

    public static void showGameResult(final LottoGameResult gameResult) {
        System.out.println("당첨 통계");
        System.out.println("---------");

        for (Rank rank : Rank.reverseValues()) {
            System.out.print(rank.getMatchCount() + "개 일치");
            if (rank == Rank.SECOND) {
                System.out.print(", 보너스 볼 일치");
            }
            System.out.print("(" + rank.getMoney() + ")- ");
            System.out.println(gameResult.getRankCount(rank) + "개");
        }
        System.out.printf("총 수익률은 %.1f 퍼센트입니다.", gameResult.profit(LottoMachine.LOTTO_MONEY));
    }

    public static void showLottos(final LottoService service) {
        LottosDTO lottos = service.getLottos();
        while (lottos.hasNext()) {
            System.out.println(lottos.next());
        }
    }
}
