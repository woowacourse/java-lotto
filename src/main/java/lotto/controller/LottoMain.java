package lotto.controller;

import lotto.domain.LottoComparator;
import lotto.domain.LottoMachine;
import lotto.domain.PurchasedLottoTickets;
import lotto.domain.UserPurchase;
import lotto.domain.WinningLottoNumbers;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoMain {
    public static void main(String[] args) {
        UserPurchase userPurchase = InputView.getUserPurchase();
        LottoMachine lottoMachine = new LottoMachine();

        PurchasedLottoTickets purchasedLottoTickets = lottoMachine.purchaseLottoTicket(userPurchase);
        OutputView.printPurchasedLottoTickets(purchasedLottoTickets);

        WinningLottoNumbers winningLottoNumbers = InputView.getWinningLottoNumbers();

        LottoComparator lottoComparator = new LottoComparator(winningLottoNumbers);
        OutputView.printResult(lottoComparator.getLottoResult(purchasedLottoTickets), userPurchase.getPurchasePrice());
    }
}
