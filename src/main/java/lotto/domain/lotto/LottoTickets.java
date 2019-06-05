package lotto.domain.lotto;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

public class LottoTickets {
    private final List<Lotto> lottoTickets;

    public LottoTickets(LottoRepository lottoRepository) {
        if (Objects.isNull(lottoRepository)) {
            throw new NullPointerException();
        }
        this.lottoTickets = lottoRepository.getLottos();
    }

    public Stream<Lotto> stream() {
        return lottoTickets.stream();
    }

    public List<Lotto> getLottoTickets() {
        return Collections.unmodifiableList(lottoTickets);
    }
}
