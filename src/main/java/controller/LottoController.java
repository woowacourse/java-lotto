package controller;

import domain.TicketMachine;
import domain.Tickets;
import domain.WinningAnalyze;
import domain.WinningNumber;
import domain.dto.WinningAnalyzeDto;
import domain.strategy.AutoStrategy;
import view.InputView;
import view.OutputView;

public class LottoController {

	public void run() {
		Tickets tickets = generateTickets(InputView.getPayment());

		// TODO: 수동 로또 구매 갯수 매개인자로
		OutputView.printTickets(tickets.getTickets(), 3);

		WinningNumber winningNumber =
			new WinningNumber(InputView.getWinningNumber(), InputView.getBonusBall());

		OutputView.printStatistics(generateStatistics(tickets, winningNumber));
	}

	private Tickets generateTickets(int money) {
		// 수동으로 구매할 로또 수 입력 받고 돈이랑 같이 기계에 넣는다.
		//
		Tickets tickets = TicketMachine.generateTickets(money, new AutoStrategy());

		return tickets;
	}

	private WinningAnalyzeDto generateStatistics(Tickets tickets, WinningNumber winningNumber) {
		WinningAnalyze winningAnalyze = new WinningAnalyze(tickets, winningNumber);

		return winningAnalyze.analyze();
	}
}
