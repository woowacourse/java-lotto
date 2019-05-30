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

    public boolean needMoreCustomLotto() {
        return lottoTickets.size() < amountOfCustom;
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
