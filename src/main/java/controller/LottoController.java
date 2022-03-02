package controller;

import java.util.List;

import domain.TicketCount;
import domain.TicketMachine;
import domain.Tickets;
import domain.WinningAnalyze;
import domain.WinningNumber;
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
		final List<List<Integer>> manualTickets =
			InputView.getManualLottoTickets(count);

		Tickets tickets = TicketMachine.generateTickets(new TicketCount(money, count), manualTickets,
			new AutoStrategy());
		OutputView.printTickets(tickets.getTickets(), manualTickets.size());

		return tickets;
	}

	private void generateStatistics(final Tickets tickets, final WinningNumber winningNumber) {
		WinningAnalyze winningAnalyze = new WinningAnalyze(tickets, winningNumber);
		OutputView.printStatistics(winningAnalyze.analyze());
	}
}
