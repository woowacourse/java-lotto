package lotto.model;

import java.util.List;
import java.util.Objects;

public record LottoTicket(
        List<Lotto> lottos
) {
    public LottoTicket {
        validateLottoCount(lottos);
    }

    private void validateLottoCount(List<Lotto> lottos) {
        Objects.requireNonNull(lottos, "로또 목록은 null이 될 수 없습니다.");
        if (lottos.isEmpty()) {
            throw new IllegalArgumentException("로또 티켓은 최소 1개 이상의 로또가 있어야 합니다.");
        }
    }

    public int getLottoCount() {
        return lottos.size();
    }
}
