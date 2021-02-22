package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import lotto.utils.LottoGenerator;

public class LottoTickets {
    private final List<LottoTicket> lottoTickets;


    public LottoTickets(int ticketCount, LottoGenerator lottoGenerator) {
        List<LottoTicket> lottoTickets = new ArrayList<>();
        for (int i = 0; i < ticketCount; i++) {
            lottoTickets.add(lottoGenerator.generateLottoTicket());
        }
        this.lottoTickets = lottoTickets;
    }

    public LottoTickets(List<LottoTicket> lottoTicketsInput, int ticketCount,
        LottoGenerator lottoGenerator) {
        List<LottoTicket> lottoTickets = new ArrayList<>(lottoTicketsInput);
        for (int i = 0; i < ticketCount; i++) {
            lottoTickets.add(lottoGenerator.generateLottoTicket());
        }
        this.lottoTickets = lottoTickets;
    }

    public List<LottoTicket> getLottoTickets() {
        return Collections.unmodifiableList(lottoTickets);
    }

    public int size() {
        return lottoTickets.size();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        LottoTickets that = (LottoTickets) o;
        return Objects.equals(lottoTickets, that.lottoTickets);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lottoTickets);
    }

}
