package lotto.controller;

import java.util.List;
import lotto.domain.LottoTicketFactory;
import lotto.domain.TicketPurchaseDecider;
import lotto.domain.vo.PurchaseAmount;
import lotto.domain.WinningStats;
import lotto.domain.lottonumber.LottoNumber;
import lotto.domain.lottonumber.LottoTicket;
import lotto.domain.lottonumber.WinningNumbers;
import lotto.domain.vo.ManualTicketCount;
import lotto.view.InputView;
import lotto.view.OutputView;
import org.jetbrains.annotations.NotNull;

public class LottoController {

    private final InputView inputView = InputView.INSTANCE;
    private final OutputView outputView = OutputView.INSTANCE;
    private final LottoTicketFactory lottoTicketFactory = LottoTicketFactory.INSTANCE;

    public void run() {
        PurchaseAmount purchaseAmount = getPurchaseAmount();
        ManualTicketCount manualTicketCount = getManualTicketSize(purchaseAmount);
        List<String> manualTicketNumbers = inputView.inputTicketNumbersManually(manualTicketCount);
        TicketPurchaseDecider ticketPurchaseDecider = new TicketPurchaseDecider(purchaseAmount, manualTicketCount);

        List<LottoTicket> lottoTickets = lottoTicketFactory.createTickets(ticketPurchaseDecider, manualTicketNumbers);
        outputView.printPurchasedTickets(lottoTickets, ticketPurchaseDecider);

        WinningStats winningStats = getWinningStats(lottoTickets);
        outputView.printWinningStats(winningStats, purchaseAmount);

        closeResource();
    }

    private PurchaseAmount getPurchaseAmount() {
        try {
            return new PurchaseAmount(inputView.inputMoney());
        } catch (Exception e) {
            inputView.printErrorMessage(e);
            return getPurchaseAmount();
        }
    }

    private ManualTicketCount getManualTicketSize(PurchaseAmount purchaseAmount) {
        try {
            return new ManualTicketCount(inputView.inputTicketSizeManually(), purchaseAmount);
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

    private WinningStats getWinningStats(List<LottoTicket> lottoTickets) {
        LottoTicket lottoTicket = getLottoTicket();
        LottoNumber bonusBall = getBonusBall();
        WinningNumbers winningNumbers = new WinningNumbers(lottoTicket, bonusBall);
        return new WinningStats(lottoTickets, winningNumbers);
    }

    private void closeResource() {
        inputView.closeResource();
    }
}
