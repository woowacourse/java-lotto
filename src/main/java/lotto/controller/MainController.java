package lotto.controller;

import lotto.model.customer.PurchaseAmount;
import lotto.model.lotto.LottoTickets;
import lotto.model.lottostore.LottoStore;
import lotto.model.winninglotto.WinningLotto;
import lotto.model.winninglotto.WinningStatistics;
import lotto.view.InputView;
import lotto.view.OutputView;

public class MainController {
    public static void main(String[] args) {
        PurchaseAmount purchaseAmount = InputView.inputPurchaseAmount();

        int manualPurchaseQuantity = InputView.inputManualPurchaseQuantity();
        LottoTickets customLottoTickets = InputView.inputCustomLottoTickets(manualPurchaseQuantity);

        LottoTickets lottoTickets = LottoStore.buyLottoTickets(purchaseAmount, customLottoTickets);
        OutputView.printLottoTickets(lottoTickets, manualPurchaseQuantity);

        WinningLotto winningLotto = InputView.inputWinningLotto();
        OutputView.printWinningStatistics(new WinningStatistics(lottoTickets, winningLotto));
    }
}
