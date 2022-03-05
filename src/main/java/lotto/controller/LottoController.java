package lotto.controller;

import java.util.ArrayList;
import java.util.List;
import lotto.domain.LottoAmount;
import lotto.domain.LottoNumber;
import lotto.domain.LottoNumbers;
import lotto.domain.LottoTicket;
import lotto.domain.ManualLottoCount;
import lotto.domain.WinningNumbers;
import lotto.domain.WinningResult;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {
    public void start() {
        LottoAmount amount = inputAmount();

        ManualLottoCount manualLottoCount = inputManualLottoCount(amount);
        LottoTicket lottoTicket = buyTicket(amount, manualLottoCount);

        WinningNumbers winningNumbers = createWinningNumbers();

        WinningResult winningResult = getWinningResult(lottoTicket, winningNumbers);

        printResult(amount, winningResult);
    }

    private LottoAmount inputAmount() {
        try {
            return new LottoAmount(InputView.inputAmount());
        } catch (IllegalArgumentException e) {
            OutputView.printException(e);
            return inputAmount();
        }
    }

    private ManualLottoCount inputManualLottoCount(LottoAmount amount) {
        try {
            return new ManualLottoCount(InputView.inputTryCount(), amount.calculateLottoCount());
        } catch (IllegalArgumentException e) {
            OutputView.printException(e);
            return inputManualLottoCount(amount);
        }
    }

    private LottoTicket buyTicket(LottoAmount amount, ManualLottoCount manualLottoCount) {
        int manualTicketCount = manualLottoCount.getValue();
        int autoTicketCount = amount.calculateLottoCount() - manualTicketCount;

        LottoTicket lottoTicket = LottoTicket.createAutoLottoTicket(autoTicketCount);

        if (manualTicketCount != 0) {
            lottoTicket.addLottoTicket(buyManualTicket(manualLottoCount));
        }

        printTickets(manualTicketCount, autoTicketCount, lottoTicket);
        return lottoTicket;
    }

    private LottoTicket buyManualTicket(ManualLottoCount manualLottoCount) {
        List<LottoNumbers> manualTickets = new ArrayList<>();
        int tryCount = manualLottoCount.getValue();

        for (int i = tryCount; i > 0; i--) {
            OutputView.printInputManualTicketSentence(i);
            manualTickets.add(inputLottoNumbers());
        }
        return LottoTicket.createManualLottoTicket(manualTickets);
    }

    private void printTickets(int manualTryCount, int autoTryCount, LottoTicket lottoTicket) {
        OutputView.printTicketCount(manualTryCount, autoTryCount);
        OutputView.printTicket(lottoTicket);
    }

    private WinningNumbers createWinningNumbers() {
        OutputView.printInputWinningTicketSentence();
        LottoNumbers inputLottoNumbers = inputLottoNumbers();
        LottoNumber bonusNumber = inputBonusNumber();

        return getWinningNumbers(inputLottoNumbers, bonusNumber);
    }

    private LottoNumbers inputLottoNumbers() {
        try {
            return new LottoNumbers(InputView.inputLottoNumbers());
        } catch (IllegalArgumentException e) {
            OutputView.printException(e);
            return inputLottoNumbers();
        }
    }

    private LottoNumber inputBonusNumber() {
        try {
            return LottoNumber.of(InputView.inputBonusBall());
        } catch (IllegalArgumentException e) {
            OutputView.printException(e);
            return inputBonusNumber();
        }
    }

    private WinningNumbers getWinningNumbers(LottoNumbers lottoNumbers, LottoNumber bonusNumber) {
        try {
            return new WinningNumbers(lottoNumbers, bonusNumber);
        } catch (IllegalArgumentException e) {
            OutputView.printException(e);
            return getWinningNumbers(lottoNumbers, inputBonusNumber());
        }
    }

    private WinningResult getWinningResult(LottoTicket lottoTicket, WinningNumbers winningNumbers) {
        return lottoTicket.calculateWinningStatistic(winningNumbers);
    }

    private void printResult(LottoAmount amount, WinningResult winningResult) {
        OutputView.printResultIntro();
        OutputView.printWinningStatistic(winningResult);
        OutputView.printProfit(amount.calculateProfit(winningResult.calculatePrizeSum()));
    }

}
