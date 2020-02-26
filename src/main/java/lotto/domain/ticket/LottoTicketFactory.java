package lotto.domain.ticket;

@FunctionalInterface
public interface LottoTicketFactory {
	LottoTicket create();
}
