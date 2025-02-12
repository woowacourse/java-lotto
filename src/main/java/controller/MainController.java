package controller;

import static domain.LottoTicket.LOTTO_PRICE;

import domain.LottoMachine;
import domain.LottoTicket;
import domain.RandomIntegerGenerator;
import java.util.List;
import view.InputView;
import view.OutputView;

public class MainController {
    public void run() {
        int purchaseAmount = InputView.inputPurchaseAmount();

        int LottoTicketNumber = purchaseAmount / LOTTO_PRICE;

        LottoMachine lottoMachine = new LottoMachine();
        List<LottoTicket> lottoTickets =
                lottoMachine.generateLottoTickets(LottoTicketNumber, new RandomIntegerGenerator());

        OutputView.printLottoTickets(lottoTickets);
        List<Integer> winningNumbers = InputView.inputWinningLottoTicket();
        InputView.inputBonusNumber(winningNumbers);
    }
}
