package lotto.controller;

import java.util.List;
import lotto.domain.LottoTicketFactory;
import lotto.domain.vo.PurchaseAmount;
import lotto.domain.WinningStats;
import lotto.domain.lottonumber.LottoNumber;
import lotto.domain.lottonumber.LottoTicket;
import lotto.domain.lottonumber.WinningNumbers;
import lotto.domain.vo.ManualTicketSize;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {

    private final InputView inputView = InputView.INSTANCE;
    private final OutputView outputView = OutputView.INSTANCE;
    private final LottoTicketFactory lottoTicketFactory = LottoTicketFactory.INSTANCE;

    public void run() {
        // #1
        PurchaseAmount purchaseAmount = new PurchaseAmount(inputView.inputMoney());
        ManualTicketSize manualTicketSize = new ManualTicketSize(inputView.inputTicketSizeManually(), purchaseAmount);
        List<String> manualTicketNumbers = inputView.inputTicketNumbersManually(manualTicketSize);

        // #2
        List<LottoTicket> lottoTickets = lottoTicketFactory.createTickets(purchaseAmount, manualTicketNumbers);
        outputView.printPurchasedTickets(lottoTickets, manualTicketNumbers.size());

        // #3
        LottoTicket lottoTicket = new LottoTicket(inputView.inputWinningNumbers());
        LottoNumber bonusBall = new LottoNumber(inputView.inputBonusBall());
        WinningNumbers winningNumbers = new WinningNumbers(lottoTicket, bonusBall);
        WinningStats winningStats = new WinningStats(lottoTickets, winningNumbers);
        outputView.printWinningStats(winningStats, purchaseAmount);

        // #4
        inputView.closeResource();
    }
}
