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
import org.jetbrains.annotations.NotNull;

public class LottoController {

    private final InputView inputView = InputView.INSTANCE;
    private final OutputView outputView = OutputView.INSTANCE;
    private final LottoTicketFactory lottoTicketFactory = LottoTicketFactory.INSTANCE;

    public void run() {
        // #1
        PurchaseAmount purchaseAmount = getPurchaseAmount();
        ManualTicketSize manualTicketSize = getManualTicketSize(purchaseAmount);
        List<String> manualTicketNumbers = inputView.inputTicketNumbersManually(manualTicketSize);

        // #2
        List<LottoTicket> lottoTickets = lottoTicketFactory.createTickets(purchaseAmount, manualTicketNumbers);
        outputView.printPurchasedTickets(lottoTickets, manualTicketNumbers.size());

        // #3
        LottoTicket lottoTicket = getLottoTicket();
        LottoNumber bonusBall = getBonusBall();
        WinningNumbers winningNumbers = new WinningNumbers(lottoTicket, bonusBall);
        WinningStats winningStats = new WinningStats(lottoTickets, winningNumbers);
        outputView.printWinningStats(winningStats, purchaseAmount);

        // #4
        inputView.closeResource();
    }

    private PurchaseAmount getPurchaseAmount() {
        try {
            return new PurchaseAmount(inputView.inputMoney());
        } catch (Exception e) {
            inputView.printErrorMessage(e);
            return getPurchaseAmount();
        }
    }

    private ManualTicketSize getManualTicketSize(PurchaseAmount purchaseAmount) {
        try {
            return new ManualTicketSize(inputView.inputTicketSizeManually(), purchaseAmount);
        } catch (Exception e) {
            inputView.printErrorMessage(e);
            return getManualTicketSize(purchaseAmount);
        }
    }

    private LottoTicket getLottoTicket() {
        try {
            return new LottoTicket(inputView.inputWinningNumbers());
        } catch (Exception e) {
            inputView.printErrorMessage(e);
            return getLottoTicket();
        }
    }

    private LottoNumber getBonusBall() {
        try {
            return new LottoNumber(inputView.inputBonusBall());
        } catch (Exception e) {
            return getBonusBall();
        }
    }
}
