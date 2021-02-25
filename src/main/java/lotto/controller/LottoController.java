package lotto.controller;

import lotto.domain.*;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.ArrayList;
import java.util.List;

public class LottoController {
    public void run() {
        Money money = new Money(InputView.inputMoney());
        ManualLottoAmount manualLottoAmount = new ManualLottoAmount(
                InputView.inputManualLottoAmount(), money.getPurchasableLottoCount());
        LottoTickets lottoTickets = getLottoTickets(money, manualLottoAmount);
        WinningLotto winningLotto = LottoTicketFactory.createWinningLotto(
                InputView.inputWinningNumbers(), InputView.inputBonusNumber());
        LottoResult lottoResult = new LottoResult(lottoTickets.checkWinningTickets(winningLotto));
        showResult(lottoResult, money);
    }

    private LottoTickets getLottoTickets(Money money, ManualLottoAmount manualLottoAmount) {
        LottoTickets purchasedTickets = LottoTicketFactory
                .createLottoTicketsIncludingManualTickets(money, getManualInputs(manualLottoAmount));
        OutputView.printLottoTicketsCount(purchasedTickets, manualLottoAmount);
        OutputView.printLottoTickets(purchasedTickets);
        return purchasedTickets;
    }

    private List<List<String>> getManualInputs(ManualLottoAmount manualLottoAmount) {
        List<List<String>> manualInputs = new ArrayList<>();
        OutputView.printInputManualLottoNumbersMessage();
        for (int i = 0; i < manualLottoAmount.getValue(); i++) {
            manualInputs.add(InputView.inputLottoNumbers());
        }
        return manualInputs;
    }

    private void showResult(LottoResult lottoResult, Money money) {
        OutputView.printResultStatistic(lottoResult);
        OutputView.printProfitRate(lottoResult, money);
    }
}
