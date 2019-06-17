package lotto;

import lotto.model.customer.PurchaseAmount;
import lotto.model.lotto.LottoTickets;
import lotto.model.lottostore.LottoStore;
import lotto.model.winninglotto.WinningLotto;
import lotto.model.winninglotto.WinningResult;
import lotto.view.InputView;
import lotto.view.OutputView;

public class ConsoleUILottoApplication {
    public static void main(String[] args) {
        PurchaseAmount purchaseAmount = InputView.inputPurchaseAmount();

        int manualPurchaseQuantity = InputView.inputManualPurchaseQuantity();
        LottoTickets customLottoTickets = InputView.inputCustomLottoTickets(manualPurchaseQuantity);

        LottoTickets lottoTickets = LottoStore.buyLottoTickets(purchaseAmount, customLottoTickets);
        OutputView.printLottoTickets(lottoTickets, manualPurchaseQuantity);

        WinningLotto winningLotto = InputView.inputWinningLotto();
        OutputView.printWinningResult(WinningResult.of(lottoTickets, winningLotto));
    }
}
