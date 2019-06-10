package lotto.domain.TicketModel;

public interface Ticket {
    public LottoNumbers numbers();

    public boolean contains(int number);

    public String toString();

    public int unitPrice();
}
