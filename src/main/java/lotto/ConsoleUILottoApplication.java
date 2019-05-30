package lotto;

import lotto.domain.lotto.InvalidLottoTicketException;
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

        WinningLotto winningLotto = createWinningLotto(createWinningLottoTicket());
        LottoResult lottoResult = new LottoResult(winningLotto, lottos);
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

    private static List<LottoTicket> createLottoTickets(PurchaseAmount purchaseAmount) {
        List<LottoTicket> lottoTickets = new ArrayList<>();
        LottoTicket lottoTicket = LottoTicket.create();

        while(purchaseAmount.buy(lottoTicket)) {
            lottoTickets.add(lottoTicket);
            lottoTicket = LottoTicket.create();
        }

        return lottoTickets;
    }

    private static LottoTicket createWinningLottoTicket() {
        try {
            return LottoTicket.create(InputView.inputWinningNumbers());
        } catch (InvalidLottoTicketException e) {
            OutputView.printErrorMessage(e.getMessage());
            return createWinningLottoTicket();
        }
    }

    private static WinningLotto createWinningLotto(LottoTicket winningTicket) {
        try {
            return WinningLotto.create(winningTicket, InputView.inputBonusNumber());
        } catch (InvalidWinningLottoException e) {
            OutputView.printErrorMessage(e.getMessage());
            return createWinningLotto(winningTicket);
        }
    }
}
