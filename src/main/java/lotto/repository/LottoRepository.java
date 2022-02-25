package lotto.repository;

import java.util.ArrayList;
import java.util.List;

import lotto.domain.LottoTicket;

public class LottoRepository {

    private static List<LottoTicket> tickets = new ArrayList<>();

    public void save(List<LottoTicket> other) {
        tickets = other;
    }

    public List<LottoTicket> get() {
        return List.copyOf(tickets);
    }
}
