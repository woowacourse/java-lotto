package lotto.repository;

import java.util.ArrayList;
import java.util.List;

import lotto.domain.LottoTicket;

public class LottoRepository {

    private List<LottoTicket> tickets = new ArrayList<>();

    private LottoRepository() {
    }

    private static class LottoRepositoryHelper {
        private static final LottoRepository INSTANCE = new LottoRepository();
    }

    public static LottoRepository getInstance() {
        return LottoRepositoryHelper.INSTANCE;
    }

    public void save(List<LottoTicket> other) {
        tickets = other;
    }

    public List<LottoTicket> get() {
        return List.copyOf(tickets);
    }
}
