package lotto.controller;

import java.util.List;

import lotto.domain.ticket.Tickets;
import lotto.dto.AnalysisDto;
import lotto.service.LottoService;
import lotto.view.LottoView;

public class LottoController {

	private final LottoService lottoService;
	private final LottoView lottoView;

	public LottoController(final LottoService lottoService, final LottoView lottoView) {
		this.lottoService = lottoService;
		this.lottoView = lottoView;
	}

	public void purchaseTickets() {
		generateTickets();
		announceTickets();
	}

	private void generateTickets() {
		final int creditMoney = lottoView.requestCreditMoney();
		lottoService.saveCredit(creditMoney);
		lottoService.generateTickets();
	}

	private void announceTickets() {
		final Tickets tickets = lottoService.getTickets();
		lottoView.announceTickets(tickets);
	}

	public void checkOutAnalysis() {
		final List<Integer> answer = lottoView.requestWinningNumbers();
		final int number = lottoView.requestBonusNumber();
		final AnalysisDto analysisDto = lottoService.generateAnalysis(answer, number);
		lottoView.announceAnalysis(analysisDto);
	}

}
