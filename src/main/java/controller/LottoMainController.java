package controller;

import domain.LottoMoney;
import domain.WinningNumbers;
import domain.WinningStatics;
import domain.tickets.AutoLottoTickets;
import dto.LottoTicketsDto;
import dto.WinningStaticsDto;
import view.InputView;
import view.OutputView;

import java.util.List;

public class LottoMainController {
    public void run() {
        final LottoMoney lottoMoney = inputLottoMoney();
        final AutoLottoTickets autoLottoTickets = buyLottoTickets(lottoMoney);
        printLottoQuantity(lottoMoney);
        printLottoTickets(autoLottoTickets);

        final WinningNumbers winningNumbers = inputWinningNumbers();
        final WinningStatics winningStatics = calculateWinningStatics(autoLottoTickets, winningNumbers);
        printWinningStatics(winningStatics);
        printProfitRate(winningStatics, lottoMoney);
    }

    private LottoMoney inputLottoMoney() {
        String input = InputView.receivePurchaseAmount();
        return new LottoMoney(input);
    }

    private AutoLottoTickets buyLottoTickets(final LottoMoney lottoMoney) {
        return new AutoLottoTickets(lottoMoney);
    }

    private void printLottoQuantity(final LottoMoney lottoMoney) {
        OutputView.printLottoQuantity(lottoMoney.toTicketQuantity());
    }

    private void printLottoTickets(final AutoLottoTickets autoLottoTickets) {
        final LottoTicketsDto lottoTicketsDto = LottoTicketsDto.of(autoLottoTickets);
        OutputView.printLottoTickets(lottoTicketsDto);
    }

    private WinningNumbers inputWinningNumbers() {
        final List<Integer> winningNumbers = InputView.receiveWinningNumbers();
        final int bonusNumber = InputView.receiveBonusNumber();

        return new WinningNumbers(winningNumbers, bonusNumber);
    }

    private WinningStatics calculateWinningStatics(final AutoLottoTickets autoLottoTickets,
                                                   final WinningNumbers winningNumbers) {

        return autoLottoTickets.calculateWinningStatics(winningNumbers);
    }

    private void printWinningStatics(final WinningStatics winningStatics) {
        OutputView.printWinningStaticsTitle();
        OutputView.printWinningStatics(WinningStaticsDto.of(winningStatics));
    }

    private void printProfitRate(final WinningStatics winningStatics, final LottoMoney lottoMoney) {
        OutputView.printProfitRate(winningStatics.calculateProfitRate(lottoMoney));
    }
}
