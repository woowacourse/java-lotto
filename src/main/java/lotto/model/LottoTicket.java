package lotto.model;

import java.util.List;
import java.util.Objects;

public class LottoTicket {

    private final List<Lotto> lottos;

    public LottoTicket(List<Lotto> lottos) {
        validateLottoCount(lottos);
        this.lottos = List.copyOf(lottos);
    }

    private void validateLottoCount(List<Lotto> lottos) {
        Objects.requireNonNull(lottos);
        if (lottos.isEmpty()) {
            throw new IllegalArgumentException("로또 티켓은 최소 1개 이상의 로또가 있어야 합니다.");
        }
    }

    public int getLottoCount() {
        return lottos.size();
    }

    public List<Lotto> getLottos() {
        return lottos;
    }
}
