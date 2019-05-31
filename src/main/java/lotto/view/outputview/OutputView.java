package lotto.view.outputview;

import lotto.domain.lottofactory.LottoTicket;
import lotto.domain.user.PurchaseAmount;

import java.util.List;

public class OutputView {
    public static void printAmount(PurchaseAmount purchaseAmount) {
        System.out.println(purchaseAmount.getLottoAmount() + "개를 구매했습니다.");
    }

    public static void printUserLottoTickets(List<LottoTicket> userLottoTickets) {
        userLottoTickets.forEach(lottoTicket -> System.out.println(lottoTicket.getLottoTicket()));
    }
}
