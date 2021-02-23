package lotto.controller;

import lotto.domain.ticketgenerator.LottoRandomGenerator;
import lotto.domain.ticketpurchase.LottoTickets;
import lotto.domain.ticketpurchase.UserPurchase;
import lotto.domain.ticketresult.LottoComparator;
import lotto.domain.ticketresult.WinningTicketAndBonusNumber;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoMain {
    public static void main(String[] args) {
        UserPurchase userPurchase = InputView.getUserPurchase();
        LottoTickets lottoTickets = new LottoTickets();
        if (userPurchase.isPurchaseManual()) {
            lottoTickets.addAll(InputView.purchaseManually(userPurchase));
        }
        LottoRandomGenerator lottoRandomGenerator = new LottoRandomGenerator();
        if (userPurchase.isPurchaseAuto()) {
            lottoTickets.addAll(lottoRandomGenerator.generate(userPurchase));
        }
        OutputView.printAllLottoTickets(lottoTickets, userPurchase);

        WinningTicketAndBonusNumber winningTicketAndBonusNumber
            = InputView.getWinningTicketAndBonusNumber();
        LottoComparator lottoComparator
            = new LottoComparator(winningTicketAndBonusNumber, userPurchase);
        OutputView.printResult(lottoComparator.getLottoResult(lottoTickets));
    }
}
