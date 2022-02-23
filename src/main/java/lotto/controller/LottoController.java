package lotto.controller;

import java.util.List;

import lotto.domain.ticket.Tickets;
import lotto.dto.AnalysisDto;
import lotto.service.LottoService;
import lotto.view.input.InputView;
import lotto.view.output.OutputView;

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
