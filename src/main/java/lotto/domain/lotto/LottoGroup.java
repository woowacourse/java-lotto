package lotto.domain.lotto;

import java.util.ArrayList;
import java.util.List;

public class LottoGroup {
    private final List<LottoNumbers> lotties;

    public LottoGroup(List<LottoNumbers> lotties) {
        this.lotties = new ArrayList<>(lotties);
    }

    public int getCount() {
        return lotties.size();
    }
}
