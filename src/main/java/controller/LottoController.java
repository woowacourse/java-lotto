package controller;

import domain.Tickets;
import domain.WinningAnalyze;
import domain.WinningNumber;
import domain.dto.WinningAnalyzeDto;
import domain.strategy.RandomTicketGenerator;
import view.InputView;
import view.OutputView;

public class LottoController {

	public void run() {
		Tickets tickets = generateTickets(InputView.getPayment());
		OutputView.printTickets(tickets);

		WinningNumber winningNumber =
			new WinningNumber(InputView.getWinningNumber(), InputView.getBonusBall());

		OutputView.printStatistics(generateStatistics(tickets, winningNumber));
	}

	private Tickets generateTickets(int payment) {
		Tickets tickets = new Tickets();
		tickets.makeTickets(payment, new RandomTicketGenerator());

		return tickets;
	}

	private WinningAnalyzeDto generateStatistics(Tickets tickets, WinningNumber winningNumber) {
		WinningAnalyze winningAnalyze = new WinningAnalyze(tickets, winningNumber);

		return winningAnalyze.analyze();
	}
}
