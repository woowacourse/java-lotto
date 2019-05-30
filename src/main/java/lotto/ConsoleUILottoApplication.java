package lotto;

import lotto.domain.lotto.LottoTicket;
import lotto.domain.lotto.LottoTicketGroup;
import lotto.domain.lottoresult.LottoResult;
import lotto.domain.lottoresult.WinningLotto;
import lotto.domain.purchaseamount.PurchaseAmount;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.ArrayList;
import java.util.List;

public class ConsoleUILottoApplication {
    public static void main(String[] args) {
        PurchaseAmount purchaseAmount = PurchaseAmount.create(InputView.inputPurchaseAmount());
        LottoTicketGroup lottos = new LottoTicketGroup(createLottoTickets(purchaseAmount));
        OutputView.printLottoTicketGroup(lottos);

        WinningLotto winningLotto = WinningLotto.create(InputView.inputWinningNumbers());
        LottoResult lottoResult = new LottoResult(winningLotto, lottos);
        OutputView.printLottoResult(lottoResult);
    }

    private static List<LottoTicket> createLottoTickets(PurchaseAmount purchaseAmount) {
        List<LottoTicket> lottoTickets = new ArrayList<>();
        LottoTicket lottoTicket = LottoTicket.create();

        while(purchaseAmount.buy(lottoTicket)) {
            lottoTickets.add(lottoTicket);
            lottoTicket = LottoTicket.create();
        }

        return lottoTickets;
    }
}
