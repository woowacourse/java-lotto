package lotto.controller;

import lotto.domain.Amount;
import lotto.domain.LottoNumber;
import lotto.domain.LottoNumbers;
import lotto.domain.LottoTickets;
import lotto.domain.WinningNumbers;
import lotto.domain.WinningResult;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {
    public void start() {
        Amount amount = inputAmount();

        LottoTickets lottoTickets = buyTickets(amount);

        WinningNumbers winningNumbers = createWinningNumbers();

        WinningResult winningResult = getWinningResult(lottoTickets, winningNumbers);

        printResult(amount, winningResult);
    }

    private Amount inputAmount() {
        try {
            return new Amount(InputView.inputAmount());
        } catch (IllegalArgumentException e) {
            OutputView.printException(e);
            return inputAmount();
        }
    }

    private LottoTickets buyTickets(Amount amount) {
        int ticketCount = amount.calculateLottoCount();
        OutputView.printTicketCount(ticketCount);

        LottoTickets lottoTickets = new LottoTickets(ticketCount);
        OutputView.printTicket(lottoTickets);
        return lottoTickets;
    }

    private WinningNumbers createWinningNumbers() {
        LottoNumbers inputLottoNumbers = getInputLottoNumbers();
        LottoNumber bonusNumber = getBonusNumber();

        return getWinningNumbers(inputLottoNumbers, bonusNumber);
    }

    private LottoNumbers getInputLottoNumbers() {
        try {
            return new LottoNumbers(InputView.inputWinningNumbers());
        } catch (IllegalArgumentException e) {
            OutputView.printException(e);
            return getInputLottoNumbers();
        }
    }

    private LottoNumber getBonusNumber() {
        try {
            return LottoNumber.of(InputView.inputBonusBall());
        } catch (IllegalArgumentException e) {
            OutputView.printException(e);
            return getBonusNumber();
        }
    }

    private WinningNumbers getWinningNumbers(LottoNumbers lottoNumbers, LottoNumber bonusNumber) {
        try {
            return new WinningNumbers(lottoNumbers, bonusNumber);
        } catch (IllegalArgumentException e) {
            OutputView.printException(e);
            return getWinningNumbers(lottoNumbers, getBonusNumber());
        }
    }

    private WinningResult getWinningResult(LottoTickets lottoTickets, WinningNumbers winningNumbers) {
        return lottoTickets.calculateWinningStatistic(winningNumbers);
    }

    private void printResult(Amount amount, WinningResult winningResult) {
        OutputView.printResultIntro();
        OutputView.printWinningStatistic(winningResult);
        OutputView.printProfit(amount.calculateProfit(winningResult.calculatePrizeSum()));
    }

}
