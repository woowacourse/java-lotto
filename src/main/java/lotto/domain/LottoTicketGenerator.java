package lotto.domain;

@FunctionalInterface
public interface LottoTicketGenerator {
	LottoTicket create();
}
