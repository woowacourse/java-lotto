package lotto.controller;

import java.util.List;
import lotto.domain.AmountToBuyLotto;
import lotto.domain.LottoNumber;
import lotto.domain.ManualLottoCount;
import lotto.domain.lottoticket.LottoTicket;
import lotto.domain.LottoTickets;
import lotto.domain.WinningNumbers;
import lotto.domain.WinningResult;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {
    public void start() {
        AmountToBuyLotto amount = inputAmount();

        ManualLottoCount manualLottoCount = inputManualLottoCount(amount);

        LottoTickets lottoTickets = buyTickets(amount, manualLottoCount);

        WinningNumbers winningNumbers = createWinningNumbers();

        WinningResult winningResult = getWinningResult(lottoTickets, winningNumbers);

        printResult(amount, winningResult);
    }

    private AmountToBuyLotto inputAmount() {
        try {
            return new AmountToBuyLotto(InputView.inputAmount());
        } catch (IllegalArgumentException e) {
            OutputView.printException(e);
            return inputAmount();
        }
    }

    private ManualLottoCount inputManualLottoCount(AmountToBuyLotto amount) {
        try {
            return ManualLottoCount.of(InputView.inputManualLottoCount(),
                    amount.calculateLottoCount());
        } catch (IllegalArgumentException e) {
            OutputView.printException(e);
            return inputManualLottoCount(amount);
        }
    }

    private LottoTickets buyTickets(AmountToBuyLotto amount, ManualLottoCount manualLottoCount) {
        int automaticLottoCount = amount.calculateAutomaticLottoCount(manualLottoCount);
        OutputView.printTicketCount(amount, manualLottoCount);

        List<LottoTicket> manualLottoTickets = inputManualTickets(manualLottoCount);

        LottoTickets lottoTickets = LottoTickets.generateRandomWithManualTickets(manualLottoTickets,
                automaticLottoCount);
        OutputView.printTicket(lottoTickets);
        return lottoTickets;
    }

    private List<LottoTicket> inputManualTickets(ManualLottoCount manualLottoCount) {
        return InputView.inputManualLottoNumbers(manualLottoCount);
    }

    private WinningNumbers createWinningNumbers() {
        LottoTicket inputLottoNumbers = getInputLottoNumbers();
        LottoNumber bonusNumber = getBonusNumber();

        return getWinningNumbers(inputLottoNumbers, bonusNumber);
    }

    private LottoTicket getInputLottoNumbers() {
        try {
            return new LottoTicket(InputView.inputWinningNumbers());
        } catch (IllegalArgumentException e) {
            OutputView.printException(e);
            return getInputLottoNumbers();
        }
    }

    private LottoNumber getBonusNumber() {
        try {
            return new LottoNumber(InputView.inputBonusBall());
        } catch (IllegalArgumentException e) {
            OutputView.printException(e);
            return getBonusNumber();
        }
    }

    private WinningNumbers getWinningNumbers(LottoTicket lottoNumbers, LottoNumber bonusNumber) {
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

    private void printResult(AmountToBuyLotto amount, WinningResult winningResult) {
        OutputView.printWinningStatistic(winningResult);
        OutputView.printProfit(amount.calculateProfit(winningResult.calculatePrizeSum()));
    }
}
