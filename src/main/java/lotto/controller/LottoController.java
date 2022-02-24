package lotto.controller;

import java.util.List;

import lotto.domain.ticket.Tickets;
import lotto.dto.AnalysisDto;
import lotto.service.LottoService;
import lotto.view.View;
import lotto.view.input.InputView;
import lotto.view.input.reader.ConsoleReader;
import lotto.view.input.reader.Reader;
import lotto.view.output.OutputView;

public class LottoController {

	private final View view;
	private final LottoService lottoService;

	public LottoController() {
		final Reader reader = new ConsoleReader();
		final InputView inputView = new InputView(reader);
		final OutputView outputView = new OutputView();

		this.view = new View(inputView, outputView);
		this.lottoService = new LottoService();
	}

	public void run() {
		announceTickets();
		announceAnalysis();
	}

	private void announceTickets() {
		final int payment = view.requestCreditMoney();
		lottoService.initPayment(payment);
		lottoService.generateTickets();

		final Tickets tickets = lottoService.getTickets();
		view.announceTickets(tickets);
	}

	private void announceAnalysis() {
		final List<Integer> answer = view.requestWinningNumbers();
		final int number = view.requestBonusNumber();

		final AnalysisDto analysisDto = lottoService.generateAnalysis(answer, number);
		view.announceAnalysis(analysisDto);
	}

}
