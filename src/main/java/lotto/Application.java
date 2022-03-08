package lotto;

import java.util.List;

import lotto.domain.LottoLine;
import lotto.domain.LottoNumber;
import lotto.domain.LottoStatistics;
import lotto.domain.LottoTicket;
import lotto.domain.Money;
import lotto.domain.WinningTicket;
import lotto.domain.generator.RandomNumberGenerator;
import lotto.domain.generator.StringInputNumberGenerator;
import lotto.domain.PurchasedLottoTickets;
import lotto.view.InputView;
import lotto.view.OutputView;

public class Application {

    private final InputView inputView;
    private final OutputView outputView;

    private Application(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    private static class ApplicationHelper {
        private static final Application INSTANCE = new Application(
            InputView.getInstance(), OutputView.getInstance()
        );
    }

    public static void main(String[] args) {
        Application application = ApplicationHelper.INSTANCE;
        application.run();
    }

    private void run() {
        PurchasedLottoTickets purchasedLottoTickets = tryPurchase();
        tryDrawLots(purchasedLottoTickets);
    }

    private PurchasedLottoTickets tryPurchase() {
        try {
            Money money = Money.from(inputView.inputMoney());
            List<String> inputLottoLines = inputView.inputLottoLines();
            PurchasedLottoTickets purchasedLottoTickets = purchaseLottos(money, inputLottoLines);
            outputView.outputPurchaseResult(purchasedLottoTickets);
            return purchasedLottoTickets;
        } catch (RuntimeException e) {
            outputView.outputError(e);
            return tryPurchase();
        }
    }

    private PurchasedLottoTickets purchaseLottos(Money money, List<String> inputLottoLines) {
        Money manualMoney = Money.from(inputLottoLines.size() * LottoLine.PRICE);
        LottoTicket manualTicket = LottoTicket.createLottoTicket(
            new StringInputNumberGenerator(inputLottoLines), manualMoney);

        Money remainMoney = money.minus(manualMoney);
        LottoTicket autoTicket = LottoTicket.createLottoTicket(
            new RandomNumberGenerator(LottoNumber.MIN, LottoNumber.MAX), remainMoney);
        return new PurchasedLottoTickets(manualTicket, autoTicket);
    }

    public void tryDrawLots(PurchasedLottoTickets purchasedLottoTickets) {
        try {
            String inputWinningNumber = inputView.inputWinningNumber();
            String inputBonusBall = inputView.inputBonusBall();
            WinningTicket winningTicket = WinningTicket.from(inputWinningNumber, inputBonusBall);
            LottoStatistics statistics =
                new LottoStatistics(purchasedLottoTickets.compareWinningTicket(winningTicket), purchasedLottoTickets.sumMoney());
            outputView.outputStatistics(statistics);
        } catch (IllegalArgumentException e) {
            outputView.outputError(e);
            tryDrawLots(purchasedLottoTickets);
        }
    }
}
