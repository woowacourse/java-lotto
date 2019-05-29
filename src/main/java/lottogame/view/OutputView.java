package lottogame.view;

import lottogame.domain.LottoResult;
import lottogame.domain.LottoTickets;

public class OutputView {
    public static void printPurchaseResult(LottoTickets lottoTickets) {
        System.out.println(lottoTickets.numberOfLottos()+"개를 구매했습니다.");
        System.out.println(lottoTickets);
    }

    public static void printLottoResult(LottoResult lottoResult, int price) {
        System.out.println("당첨 통계");
        System.out.println("---------");
        System.out.println(lottoResult);
        System.out.println("총 수익률은 " + lottoResult.getRateOfLotto(price)+"%입니다.");
    }
}
