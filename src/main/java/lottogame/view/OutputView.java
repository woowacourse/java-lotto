package lottogame.view;

import lottogame.domain.LottoResult;
import lottogame.domain.LottoTickets;
import lottogame.domain.Money;

public class OutputView {
    public static void printPurchaseResult(LottoTickets lottoTickets, Money money) {
        System.out.println("수동으로 "+ lottoTickets.getNumberOfManualTicket() +"개, 자동으로 " + lottoTickets.numberOfLottos()+"개를 구매했습니다.");
        System.out.println(lottoTickets);
    }

    public static void printRequestOfManualLottoNumber() {
        System.out.println("수동으로 구매할 로또 번호를 입력해 주세요.");
    }

    public static void printLottoResult(LottoResult lottoResult, Money money) {
        System.out.println("당첨 통계");
        System.out.println("---------");
        System.out.println(lottoResult);
        System.out.println("총 수익률은 " + lottoResult.getRateOfLotto(money)+"%입니다.");
    }
}
