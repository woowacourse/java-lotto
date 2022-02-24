package controller;

import java.util.List;

import domain.Tickets;
import dto.AnalysisDto;
import domain.LottoMachine;
import view.InputView;
import view.OutputView;

public class LottoController {
	private final LottoMachine lottoService = new LottoMachine();

	public void run() {
		generateTickets();
		announceAnalysis();
	}

	private void generateTickets() {
		int payment = InputView.getPayment();
		lottoService.initPayment(payment);
		lottoService.generateTickets();

		Tickets tickets = lottoService.getTickets();
		OutputView.printTickets(tickets);
	}

	private void announceAnalysis() {
		List<Integer> answer = InputView.getAnswerNumbers();
		int number = InputView.getBonusBall();

		AnalysisDto analysisDto = lottoService.generateAnalysis(answer, number);
		OutputView.printAnalysis(analysisDto);
	}
}
