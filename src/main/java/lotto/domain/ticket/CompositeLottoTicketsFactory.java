package lotto.domain.ticket;

public class CompositeLottoTicketsFactory {
	private final LottoTicketsFactory manualTicketFactory;
	private final LottoTicketsFactory autoTicketFactory;

	public CompositeLottoTicketsFactory(LottoTicketsFactory manualTicketFactory,
		LottoTicketsFactory autoTicketFactory) {
		this.manualTicketFactory = manualTicketFactory;
		this.autoTicketFactory = autoTicketFactory;
	}

	public LottoTickets create() {
		LottoTickets manualLottoTickets = manualTicketFactory.create();
		LottoTickets autoLottoTickets = autoTicketFactory.create();
		return manualLottoTickets.concat(autoLottoTickets);
	}
}
