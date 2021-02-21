package lotto.controller;

import lotto.domain.ticketgenerator.LottoGenerator;
import lotto.domain.ticketpurchase.LottoTickets;
import lotto.domain.ticketpurchase.UserPurchase;
import lotto.domain.ticketresult.LottoComparator;
import lotto.domain.ticketresult.WinningTicketAndBonusNumber;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoMain {
    public static void main(String[] args) {
        UserPurchase userPurchase = InputView.getUserPurchase();
        LottoGenerator lottoGenerator = new LottoGenerator();
        LottoTickets lottoTickets = lottoGenerator.purchaseTickets(userPurchase);
        OutputView.printLottoTickets(lottoTickets);

        WinningTicketAndBonusNumber winningLottoNumbers = InputView.getWinningTicketAndBonusNumber();
        LottoComparator lottoComparator = new LottoComparator(winningLottoNumbers, userPurchase);
        OutputView.printResult(lottoComparator.getLottoResult(lottoTickets));
    }
}
