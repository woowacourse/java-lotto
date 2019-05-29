package lotto.view;

import lotto.domain.LottoGameResult;
import lotto.domain.LottoMachine;
import lotto.domain.Rank;

public class OutputView {
    private OutputView() {

    }

    public static void showBuyCounts(final int manualPurchaseCount, final int autoPurchaseCount) {
        System.out.printf("수동으로 %d장, 자동으로 %d개를 구매했습니다.\n", manualPurchaseCount, autoPurchaseCount);
    }

    public static void showGameResult(final LottoGameResult gameResult) {
        System.out.println("당첨 통계");
        System.out.println("---------");

        for (Rank rank : Rank.values()) {

        }
        System.out.printf("총 수익률은 %f 입니다.", gameResult.profit(LottoMachine.LOTTO_MONEY));
    }
}
