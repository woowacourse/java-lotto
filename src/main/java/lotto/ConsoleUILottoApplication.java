package lotto;

import lotto.domain.lotto.LottoTicket;
import lotto.domain.lotto.LottoTicketGroup;
import lotto.domain.lottoresult.InvalidWinningLottoException;
import lotto.domain.lottoresult.LottoResult;
import lotto.domain.lottoresult.WinningLotto;
import lotto.domain.purchaseamount.PurchaseAmount;
import lotto.domain.purchaseamount.PurchaseAmountException;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.ArrayList;
import java.util.List;

public class ConsoleUILottoApplication {
    public static void main(String[] args) {
        PurchaseAmount purchaseAmount = createPurchaseAmount();
        LottoTicketGroup lottos = new LottoTicketGroup(createLottoTickets(purchaseAmount));
        OutputView.printChange(purchaseAmount);
        OutputView.printLottoTicketGroup(lottos);

        LottoResult lottoResult = new LottoResult(createWinningLotto(), lottos);
        OutputView.printLottoResult(lottoResult);
    }

    private static PurchaseAmount createPurchaseAmount() {
        try {
            return PurchaseAmount.create(InputView.inputPurchaseAmount());
        } catch (PurchaseAmountException e) {
            OutputView.printErrorMessage(e.getMessage());
            return createPurchaseAmount();
        }
    }

    private static WinningLotto createWinningLotto() {
        try {
            return WinningLotto.create(InputView.inputWinningNumbers());
        } catch (InvalidWinningLottoException e) {
            OutputView.printErrorMessage(e.getMessage());
            return createWinningLotto();
        }
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
