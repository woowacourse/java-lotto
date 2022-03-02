package controller;

import java.util.List;

import domain.TicketMachine;
import domain.Tickets;
import domain.WinningAnalyze;
import domain.WinningNumber;
import domain.dto.WinningAnalyzeDto;
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
		final List<List<Integer>> manualTickets =
			InputView.getManualLottoTickets(InputView.getManualLottoNumber());

		Tickets tickets = TicketMachine.generateTickets(money, manualTickets);
		OutputView.printTickets(tickets.getTickets(), manualTickets.size());

		return tickets;
	}

	private void generateStatistics(final Tickets tickets, final WinningNumber winningNumber) {
		WinningAnalyze winningAnalyze = new WinningAnalyze(tickets, winningNumber);
		OutputView.printStatistics(winningAnalyze.analyze());
	}
}
