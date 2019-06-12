package lotto.domain.ticket;


import java.util.ArrayList;
import java.util.List;

public class LottoCreator implements TicketCreator {
    private static final int LOTTO_PRICE = 1000;

    public LottoCreator() {

    }

    @Override
    public Ticket create(List<Integer> numbers) {
        List<TicketNumber> ticketNumbers = new ArrayList<>();
        for (Integer number : numbers) {
            ticketNumbers.add(new LottoNumber(number));
        }
        return new Lotto(new LottoNumbers(ticketNumbers));
    }

    @Override
    public Ticket create() {
        return new Lotto(new LottoNumbers());
    }

    @Override
    public int unitPrice() {
        return LOTTO_PRICE;
    }
}
