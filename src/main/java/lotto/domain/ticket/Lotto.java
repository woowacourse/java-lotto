package lotto.domain.ticket;

import java.util.Objects;

class Lotto implements Ticket {
    private final TicketNumbers lottoNumbers;

    public Lotto(TicketNumbers lottoNumbers) {
        this.lottoNumbers = lottoNumbers;
    }

    @Override
    public TicketNumbers lottoNumbers() {
        return lottoNumbers;
    }

    @Override
    public boolean contains(TicketNumber number) {
        return lottoNumbers.contains(number);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lotto lotto = (Lotto) o;
        return lottoNumbers.equals(lotto.lottoNumbers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lottoNumbers);
    }
}
