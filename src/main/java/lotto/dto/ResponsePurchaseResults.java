package lotto.dto;

import java.util.List;
import lotto.domain.vo.Lotto;
import lotto.domain.vo.Money;

public class ResponsePurchaseResults {

    private final List<Lotto> lottos;
    private final Money changes;

    public ResponsePurchaseResults(List<Lotto> lottos, Money changes) {
        this.lottos = lottos;
        this.changes = changes;
    }

    public List<Lotto> getLottos() {
        return lottos;
    }

    public Money getChanges() {
        return changes;
    }
}
