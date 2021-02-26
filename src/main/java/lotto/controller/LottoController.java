package lotto.controller;

import lotto.domain.*;
import lotto.exception.*;
import lotto.view.ErrorView;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.ArrayList;
import java.util.List;

public class LottoController {
    public void run() {
        Money money = receiveMoneyInput();
        ManualLottoAmount manualLottoAmount = receiveManualLottoAmountInput(money);
        LottoTickets lottoTickets = getLottoTickets(money, manualLottoAmount);
        WinningLotto winningLotto = receiveWinningLottoInput();
        LottoResult lottoResult = new LottoResult(lottoTickets.checkWinningTickets(winningLotto));
        showResult(lottoResult, money);
    }

    private Money receiveMoneyInput() {
        Money money;
        try {
            money = new Money(InputView.inputMoney());
        } catch (IllegalMoneyException e) {
            ErrorView.printErrorMessage(e.getMessage());
            money = receiveMoneyInput();
        }
        return money;
    }

    private ManualLottoAmount receiveManualLottoAmountInput(Money money) {
        ManualLottoAmount manualLottoAmount;
        try {
            manualLottoAmount = new ManualLottoAmount(InputView.inputManualLottoAmount(), money);
        } catch (IllegalManualLottoAmountException e) {
            ErrorView.printErrorMessage(e.getMessage());
            manualLottoAmount = receiveManualLottoAmountInput(money);
        }
        return manualLottoAmount;
    }

    private LottoTickets getLottoTickets(Money money, ManualLottoAmount manualLottoAmount) {
        LottoTickets lottoTickets = receiveLottoTicketsInput(money, manualLottoAmount);
        OutputView.printLottoTicketsCount(lottoTickets, manualLottoAmount);
        OutputView.printLottoTickets(lottoTickets);
        return lottoTickets;
    }

    private LottoTickets receiveLottoTicketsInput(Money money, ManualLottoAmount manualLottoAmount) {
        LottoTickets lottoTickets;
        try {
            lottoTickets = LottoTicketFactory.createLottoTicketsIncludingManualTickets(money, getManualInputs(manualLottoAmount));
        } catch (IllegalLottoNumberException | IllegalLottoNumbersException e) {
            ErrorView.printErrorMessage(e.getMessage());
            lottoTickets = receiveLottoTicketsInput(money, manualLottoAmount);
        }
        return lottoTickets;
    }

    private List<List<String>> getManualInputs(ManualLottoAmount manualLottoAmount) {
        List<List<String>> manualInputs = new ArrayList<>();
        OutputView.printInputManualLottoNumbersMessage();
        for (int i = 0; i < manualLottoAmount.getValue(); i++) {
            manualInputs.add(InputView.inputLottoNumbers());
        }
        return manualInputs;
    }

    private WinningLotto receiveWinningLottoInput() {
        WinningLotto winningLotto;
        try {
            winningLotto = LottoTicketFactory.createWinningLotto(
                    InputView.inputWinningNumbers(), InputView.inputBonusNumber());
        } catch (IllegalLottoNumberException | IllegalLottoNumbersException | IllegalWinningLottoException e) {
            ErrorView.printErrorMessage(e.getMessage());
            winningLotto = receiveWinningLottoInput();
        }
        return winningLotto;
    }

    private void showResult(LottoResult lottoResult, Money money) {
        OutputView.printResultStatistic(lottoResult);
        OutputView.printProfitRate(lottoResult, money);
    }
}
