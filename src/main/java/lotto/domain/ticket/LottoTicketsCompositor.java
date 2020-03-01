package lotto.domain.ticket;

public class LottoTicketsCompositor {
	private final LottoTicketsFactory manualTicketFactory;
	private final LottoTicketsFactory autoTicketFactory;

	public LottoTicketsCompositor(LottoTicketsFactory manualTicketFactory,
		LottoTicketsFactory autoTicketFactory) {
		this.manualTicketFactory = manualTicketFactory;
		this.autoTicketFactory = autoTicketFactory;
	}

	public LottoTickets composite() {
		LottoTickets manualLottoTickets = manualTicketFactory.create();
		LottoTickets autoLottoTickets = autoTicketFactory.create();
		return manualLottoTickets.concat(autoLottoTickets);
	}
}
