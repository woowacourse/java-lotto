package domain;

public interface LottoTicketGenerator {
    LottoTicket generateLottoTicket(int startInclusive, int endInclusive);
}
