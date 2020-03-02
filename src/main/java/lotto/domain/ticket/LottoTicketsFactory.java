package lotto.domain.ticket;

public class LottoTicketsFactory {
	public static LottoTickets of(TicketGenerator ticketGenerator) {
		return ticketGenerator.create();
	}
}
