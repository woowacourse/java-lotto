package controller;

import java.util.List;

import domain.Tickets;
import dto.AnalysisDto;
import domain.LottoMachine;
import view.InputView;
import view.OutputView;

public class LottoController {
	private LottoMachine lottoMachine;

	public void run() {
		generateTickets();
		announceAnalysis();
	}

	private void generateTickets() {
		int payment = InputView.getPayment();
		lottoMachine = new LottoMachine(payment);
		lottoMachine.generateTickets();

		Tickets tickets = lottoMachine.getTickets();
		OutputView.printTickets(tickets);
	}

	private void announceAnalysis() {
		List<Integer> answer = InputView.getAnswerNumbers();
		int number = InputView.getBonusBall();

		AnalysisDto analysisDto = lottoMachine.generateAnalysis(answer, number);
		OutputView.printAnalysis(analysisDto);
	}
}
