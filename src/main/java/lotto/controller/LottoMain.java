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
            = lottoMachine.purchaseLottoTicket(userPurchase);
        OutputView.printPurchasedLottoTickets(purchasedLottoTickets);

        WinningLottoNumbers winningLottoNumbers = getWinningLottoNumbersInput();
        LottoComparator lottoComparator = new LottoComparator(winningLottoNumbers);
        OutputView.printResult(lottoComparator.getLottoResult(purchasedLottoTickets),
            userPurchase.getPurchasePrice());
    }

    private static UserPurchase getUserPurchaseInput() {
        try {
            return InputView.getUserPurchase();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return getUserPurchaseInput();
        }
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
