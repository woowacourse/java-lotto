package controller;

import java.util.HashMap;
import java.util.Map;
import view.inputview.BonusNumberInputView;
import view.inputview.InputView;
import view.inputview.PurchaseMoneyInputView;
import view.inputview.WinningNumberInputView;
import view.outputview.LottoTicketOutputView;
import view.outputview.OutputView;
import view.outputview.RateOfReturnOutputView;
import view.outputview.WinningResultOutputView;

public class ViewContainer {
    private final PurchaseMoneyInputView purchaseMoneyInputView = new PurchaseMoneyInputView();
    private final LottoTicketOutputView lottoTicketOutputView = new LottoTicketOutputView();
    private final WinningNumberInputView winningNumberInputView = new WinningNumberInputView();
    private final BonusNumberInputView bonusNumberInputView = new BonusNumberInputView();
    private final WinningResultOutputView winningResultOutputView = new WinningResultOutputView();
    private final RateOfReturnOutputView rateOfReturnOutputView = new RateOfReturnOutputView();

    public PurchaseMoneyInputView getPurchaseMoneyInputView() {
        return purchaseMoneyInputView;
    }

    public LottoTicketOutputView getLottoTicketOutputView() {
        return lottoTicketOutputView;
    }

    public WinningNumberInputView getWinningNumberInputView() {
        return winningNumberInputView;
    }

    public BonusNumberInputView getBonusNumberInputView() {
        return bonusNumberInputView;
    }

    public WinningResultOutputView getWinningResultOutputView() {
        return winningResultOutputView;
    }

    public RateOfReturnOutputView getRateOfReturnOutputView() {
        return rateOfReturnOutputView;
    }
}
