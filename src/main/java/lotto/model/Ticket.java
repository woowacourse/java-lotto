package lotto.model;

import java.util.List;

public class Ticket {

    private List<LottoNumber> autoTicket;

    public Ticket(List<LottoNumber> ticket) {
        autoTicket = ticket;
    }

    public boolean contains(LottoNumber lottoNumber) {
        return autoTicket.contains(lottoNumber);
    }

    public List<LottoNumber> getTicket() {
        return autoTicket;
    }
}
