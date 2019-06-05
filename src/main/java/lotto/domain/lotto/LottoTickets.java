package lotto.domain.lotto;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class LottoTickets {
    private final List<Lotto> lottoTickets;

    public LottoTickets(LottoRepository lottoRepository) {
        if (Objects.isNull(lottoRepository)) {
            throw new NullPointerException();
        }
        this.lottoTickets = lottoRepository.getLottos();
    }

    public List<Lotto> getLottoTickets() {
        return Collections.unmodifiableList(lottoTickets);
    }
}
