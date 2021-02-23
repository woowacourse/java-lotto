package lotto.controller;

import lotto.domain.ticketgenerator.LottoTicketsRandomGenerator;
import lotto.domain.ticketpurchase.LottoTickets;
import lotto.domain.ticketpurchase.UserPurchase;
import lotto.domain.ticketresult.LottoComparator;
import lotto.domain.ticketresult.WinningTicketAndBonusNumber;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoMain {
    public static void main(String[] args) {
        UserPurchase userPurchase = InputView.getUserPurchase();
        LottoTickets lottoTickets = purchaseTickets(userPurchase);
        OutputView.printAllLottoTickets(lottoTickets, userPurchase);
        WinningTicketAndBonusNumber winningTicketAndBonusNumber
            = InputView.getWinningTicketAndBonusNumber();
        LottoComparator lottoComparator
            = new LottoComparator(winningTicketAndBonusNumber, userPurchase);
        OutputView.printResult(lottoComparator.getLottoResult(lottoTickets));
    }

    private static LottoTickets purchaseTickets(UserPurchase userPurchase) {
        LottoTickets lottoTickets = new LottoTickets();
        if (userPurchase.isPurchaseManually()) {
            lottoTickets.addAll(InputView.purchaseManually(userPurchase));
        }
        if (userPurchase.isPurchaseRandomly()) {
            lottoTickets.addAll(new LottoTicketsRandomGenerator().generate(userPurchase));
        }
        return lottoTickets;
    }
}
