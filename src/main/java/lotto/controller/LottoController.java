package lotto.controller;

import java.util.List;

import lotto.dto.AnalysisDto;
import lotto.dto.TicketDto;
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
		final List<TicketDto> ticketDtos = lottoService.getTicketDtos();
		lottoView.announceTickets(ticketDtos);
	}

	public void checkOutAnalysis() {
		final List<Integer> answer = lottoView.requestWinningNumbers();
		final int number = lottoView.requestBonusNumber();
		final AnalysisDto analysisDto = lottoService.generateAnalysis(answer, number);
		lottoView.announceAnalysis(analysisDto);
	}

}
