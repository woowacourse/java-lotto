package lotto.controller;

import lotto.domain.*;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.ArrayList;
import java.util.List;

public class LottoController {
    public void run() {
        Money money = new Money(InputView.inputMoney());
        ManualLottoAmount manualLottoAmount = new ManualLottoAmount(InputView.inputManualLottoAmount(), money.getPurchasableLottoCount());
        LottoTickets lottoTickets = getLottoTickets(money, manualLottoAmount);
        LottoTicket winningTicket = LottoTicketFactory.createManualLottoTicket(InputView.inputWinningNumbers());
        LottoResult lottoResult = getLottoResult(lottoTickets, winningTicket);
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

    private LottoResult getLottoResult(LottoTickets lottoTickets, LottoTicket winningTicket) {
        LottoNumber bonusNumber = LottoNumber.of(InputView.inputBonusNumber());
        WinningLotto winningLotto = new WinningLotto(winningTicket, bonusNumber);
        return new LottoResult(lottoTickets.checkWinningTickets(winningLotto));
    }

    private void showResult(LottoResult lottoResult, Money money) {
        OutputView.printResultStatistic(lottoResult);
        OutputView.printProfitRate(lottoResult, money);
    }
}
