package controller;

import java.util.List;

import domain.Tickets;
import domain.WinningAnalyze;
import domain.WinningNumber;
import domain.strategy.RandomTicketGenerator;
import view.InputView;
import view.OutputView;

public class LottoController {
	public void run() {
		int payment = InputView.getPayment();
		Tickets tickets = generateTickets(payment);
		OutputView.printTickets(tickets);

		List<Integer> numbers = InputView.getWinningNumber();
		int bonusBall = InputView.getBonusBall();
		WinningNumber winningNumber = new WinningNumber(numbers, bonusBall);

		WinningAnalyze winningAnalyze = generateStatistics(tickets, winningNumber);
		OutputView.printStatistics(winningAnalyze);
	}

	private Tickets generateTickets(int payment) {
		Tickets tickets = new Tickets();
		tickets.makeTickets(payment, new RandomTicketGenerator());

		return tickets;
	}

	private WinningAnalyze generateStatistics(Tickets tickets, WinningNumber winningNumber) {
		WinningAnalyze winningAnalyze = new WinningAnalyze();
		winningAnalyze.analyze(tickets, winningNumber);

		return winningAnalyze;
	}

}
