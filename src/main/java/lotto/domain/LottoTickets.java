package lotto.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class LottoTickets {
    private final List<LottoTicket> lottoTickets = new ArrayList<>();
    private final int amountOfCustom;

    public LottoTickets(final int amountOfCustom) {
        this.amountOfCustom = amountOfCustom;
    }

    public boolean needMoreCustomLottoTicket() {
        return lottoTickets.size() < amountOfCustom;
    }

    public void putCustomLottoTicket(final String inputCustomNumber) {
        lottoTickets.add(LottoTicketFactory.create(inputCustomNumber));
    }

    public List<LottoTicket> getLottoTickets() {
        return lottoTickets;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LottoTickets that = (LottoTickets) o;
        return amountOfCustom == that.amountOfCustom &&
                Objects.equals(lottoTickets, that.lottoTickets);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lottoTickets, amountOfCustom);
    }
}
