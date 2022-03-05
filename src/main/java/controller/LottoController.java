package controller;

import domain.TicketCounter;
import domain.TicketMachine;
import domain.Tickets;
import domain.WinningAnalyze;
import domain.WinningNumber;
import domain.dto.ManualTicketsDto;
import domain.strategy.AutoStrategy;
import view.InputView;
import view.OutputView;

public class LottoController {

	public void run() {
		Tickets tickets = generateTickets();

		WinningNumber winningNumber =
			new WinningNumber(InputView.getWinningNumber(), InputView.getBonusBall());

		generateStatistics(tickets, winningNumber);
	}

	private Tickets generateTickets() {
		final int money = InputView.getPayment();
		final int count = InputView.getManualLottoNumber();
		final ManualTicketsDto manualTicketsDto =
			InputView.getManualLottoTickets(count);

		Tickets tickets = TicketMachine.buyTickets(new TicketCounter(money, count), manualTicketsDto,
			new AutoStrategy());

		OutputView.printTickets(tickets.getTickets(), manualTicketsDto.size());

		return tickets;
	}

	private void generateStatistics(final Tickets tickets, final WinningNumber winningNumber) {
		WinningAnalyze winningAnalyze = new WinningAnalyze(tickets, winningNumber);
		OutputView.printStatistics(winningAnalyze.analyze());
	}
}
