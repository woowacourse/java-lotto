package lotto.domain;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class LottoTickets {
    private List<Lotto> lottoTickets;

    public LottoTickets(LottoRepository lottoRepository) {
        if (Objects.isNull(lottoRepository)) {
            throw new NullPointerException();
        }
        this.lottoTickets = lottoRepository.getLottos();
    }

    public int size() {
        return lottoTickets.size();
    }

    public List<Lotto> getLottoTickets() {
        return Collections.unmodifiableList(lottoTickets);
    }
}
