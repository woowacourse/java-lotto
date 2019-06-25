package lotto.view;

import lotto.model.lotto.LottoTickets;
import lotto.model.winninglotto.WinningResult;

public class OutputView {
    public static void printWinningResult(WinningResult winningResult) {
        System.out.println("당첨 통계");
        System.out.println("--------");
        System.out.println(winningResult);
    }

    public static void printLottoTickets(LottoTickets lottoTickets, int manualPurchaseQuantity) {
        System.out.print("수동으로 " + manualPurchaseQuantity + "장,");
        System.out.println("자동으로 " + (lottoTickets.size() - manualPurchaseQuantity) + " 개를 구매했습니다.");
        System.out.println(lottoTickets);
    }
}
