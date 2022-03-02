package controller.dto;

import java.util.List;
import java.util.stream.Collectors;

import domain.LottoTicket;

public class LottoTicketDto {

	private final List<List<Integer>> lottoTicketNumbers;
	private final int manualCount;
	private final int autoCount;

	private LottoTicketDto(List<List<Integer>> lottoTicketNumbers, int manualCount, int autoCount) {
		this.lottoTicketNumbers = lottoTicketNumbers;
		this.manualCount = manualCount;
		this.autoCount = autoCount;
	}

	public static LottoTicketDto of(List<LottoTicket> lottoTickets, int manualCount, int autoCount) {
		List<List<Integer>> lottoTicketNumbers = lottoTickets.stream()
			.map(LottoTicket::getTicketNumbers)
			.collect(Collectors.toList());

		return new LottoTicketDto(lottoTicketNumbers, manualCount, autoCount);
	}

	public List<List<Integer>> getLottoTicketNumbers() {
		return lottoTicketNumbers;
	}

	public int getManualCount() {
		return manualCount;
	}

	public int getAutoCount() {
		return autoCount;
	}
}
