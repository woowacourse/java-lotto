package controller;

import java.util.List;

import domain.Tickets;
import dto.AnalysisDto;
import service.LottoService;
import view.InputView;
import view.OutputView;

public class LottoController {
	private final LottoService lottoService = new LottoService();

	public void run() {
		announceTickets();
		announceAnalysis();
	}

	private void announceTickets() {
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
