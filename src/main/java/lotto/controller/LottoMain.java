package lotto.controller;

import lotto.domain.ticketgenerator.LottoMachine;
import lotto.domain.ticketpurchase.PurchasedLottoTickets;
import lotto.domain.ticketpurchase.UserPurchase;
import lotto.domain.ticketresult.LottoComparator;
import lotto.domain.ticketresult.WinningLottoNumbers;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoMain {
    public static void main(String[] args) {
        LottoMachine lottoMachine = new LottoMachine();
        UserPurchase userPurchase = getUserPurchaseInput();
        PurchasedLottoTickets purchasedLottoTickets
            = purchaseLottoTickets(lottoMachine, userPurchase);
        checkLottoResult(purchasedLottoTickets, userPurchase);
    }

    private static UserPurchase getUserPurchaseInput() {
        try {
            return InputView.getUserPurchase();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return getUserPurchaseInput();
        }
    }

    private static PurchasedLottoTickets purchaseLottoTickets(LottoMachine lottoMachine,
        UserPurchase userPurchase) {
        PurchasedLottoTickets purchasedLottoTickets
            = lottoMachine.purchaseLottoTicket(userPurchase);
        OutputView.printPurchasedLottoTickets(purchasedLottoTickets);
        return purchasedLottoTickets;
    }

    private static void checkLottoResult(PurchasedLottoTickets purchasedLottoTickets,
        UserPurchase userPurchase) {
        WinningLottoNumbers winningLottoNumbers = getWinningLottoNumbersInput();
        LottoComparator lottoComparator = new LottoComparator(winningLottoNumbers, userPurchase);
        OutputView.printResult(lottoComparator.getLottoResult(purchasedLottoTickets));
    }

    private static WinningLottoNumbers getWinningLottoNumbersInput() {
        try {
            return InputView.getWinningLottoNumbers();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return getWinningLottoNumbersInput();
        }
    }
}
