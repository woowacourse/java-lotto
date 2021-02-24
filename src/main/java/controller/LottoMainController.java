package controller;

import domain.LottoMoney;
import domain.TicketQuantity;
import domain.WinningNumbers;
import domain.WinningStatics;
import domain.tickets.LottoTickets;
import dto.LottoTicketsDto;
import dto.WinningStaticsDto;
import view.InputView;
import view.OutputView;

import java.util.List;

public class LottoMainController {
    public void run() {
        final LottoMoney lottoMoney = inputLottoMoney();
        final TicketQuantity ticketQuantity = new TicketQuantity(lottoMoney, InputView.receiveManualTicketQuantity());
        final LottoTickets lottoTickets = buyLottoTickets(lottoMoney);
        printLottoQuantity(lottoMoney);
        printLottoTickets(lottoTickets);

        final WinningNumbers winningNumbers = inputWinningNumbers();
        final WinningStatics winningStatics = calculateWinningStatics(lottoTickets, winningNumbers);
        printWinningStatics(winningStatics);
        printProfitRate(winningStatics, lottoMoney);
    }

    private LottoMoney inputLottoMoney() {
        String input = InputView.receivePurchaseMoney();
        return new LottoMoney(input);
    }

    private LottoTickets buyLottoTickets(final LottoMoney lottoMoney) {
        return new LottoTickets(lottoMoney);
    }

    private void printLottoQuantity(final LottoMoney lottoMoney) {
        OutputView.printLottoQuantity(lottoMoney.toTicketQuantity());
    }

    private void printLottoTickets(final LottoTickets lottoTickets) {
        final LottoTicketsDto lottoTicketsDto = LottoTicketsDto.of(lottoTickets);
        OutputView.printLottoTickets(lottoTicketsDto);
    }

    private WinningNumbers inputWinningNumbers() {
        final List<Integer> winningNumbers = InputView.receiveWinningNumbers();
        final int bonusNumber = InputView.receiveBonusNumber();

        return new WinningNumbers(winningNumbers, bonusNumber);
    }

    private WinningStatics calculateWinningStatics(final LottoTickets lottoTickets,
                                                   final WinningNumbers winningNumbers) {

        return lottoTickets.calculateWinningStatics(winningNumbers);
    }

    private void printWinningStatics(final WinningStatics winningStatics) {
        OutputView.printWinningStaticsTitle();
        OutputView.printWinningStatics(WinningStaticsDto.of(winningStatics));
    }

    private void printProfitRate(final WinningStatics winningStatics, final LottoMoney lottoMoney) {
        OutputView.printProfitRate(winningStatics.calculateProfitRate(lottoMoney));
    }
}
