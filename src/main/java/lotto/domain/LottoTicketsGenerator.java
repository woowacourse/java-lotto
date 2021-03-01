package lotto.domain;

public interface LottoTicketsGenerator {
    LottoTickets create(Money money);
}
