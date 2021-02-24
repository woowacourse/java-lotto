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

import java.util.Collections;
import java.util.List;

public class LottoMainController {
    public void run() {
        final LottoMoney lottoMoney = inputLottoMoney();
        final int manualLottoQuantity = InputView.receiveManualTicketQuantity();
        final List<List<Integer>> expectedManualNumbers = inputExpectedManualNumbers(manualLottoQuantity);

        final TicketQuantity ticketQuantity = new TicketQuantity(lottoMoney, manualLottoQuantity);
        final LottoTickets lottoTickets = buyLottoTickets(ticketQuantity, expectedManualNumbers);
        printPurchaseInformation(ticketQuantity, lottoTickets);

        final WinningNumbers winningNumbers = inputWinningNumbers();
        printWinningStatics(lottoTickets, winningNumbers, lottoMoney);
    }

    private LottoMoney inputLottoMoney() {
        String input = InputView.receivePurchaseMoney();
        return new LottoMoney(input);
    }

    private List<List<Integer>> inputExpectedManualNumbers(final int manualLottoQuantity) {
        if (manualLottoQuantity == 0) {
            return Collections.emptyList();
        }
        return InputView.receiveExpectedManualNumbers(manualLottoQuantity);
    }

    private LottoTickets buyLottoTickets(final TicketQuantity ticketQuantity, final List<List<Integer>> expectedManualNumbers) {
        return new LottoTickets(ticketQuantity, expectedManualNumbers);
    }

    private void printPurchaseInformation(final TicketQuantity ticketQuantity, final LottoTickets lottoTickets) {
        OutputView.printLottoQuantity(ticketQuantity.getManualAmount(), ticketQuantity.getAutoAmount());
        printLottoTickets(lottoTickets);
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

    private void printWinningStatics(final LottoTickets lottoTickets,
                                     final WinningNumbers winningNumbers, final LottoMoney lottoMoney) {

        final WinningStatics winningStatics = lottoTickets.calculateWinningStatics(winningNumbers);

        OutputView.printWinningStaticsTitle();
        OutputView.printWinningStatics(WinningStaticsDto.of(winningStatics));
        OutputView.printProfitRate(winningStatics.calculateProfitRate(lottoMoney));
    }
}
