package domain;

import java.util.ArrayList;
import java.util.List;

public class Tickets {
    private List<Ticket> tickets = new ArrayList<>();

    public Tickets(int count) {
        LottoNumbersGenerator lottoNumbersGenerator = new RandomLottoNumbersGenerator();
        for (int i = 0; i < count; i++) {
            tickets.add(new Ticket(lottoNumbersGenerator));
        }
    }

    public List<Ticket> getTickets() {
        return tickets;
    }
}
