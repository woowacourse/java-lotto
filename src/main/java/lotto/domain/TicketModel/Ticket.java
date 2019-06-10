package lotto.domain.TicketModel;

public interface Ticket {

    public LottoNumbers lottoNumbers();

    public boolean contains(int number);

    public String toString();
}
