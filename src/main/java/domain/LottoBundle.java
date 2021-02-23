package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoBundle {
    private final List<Lotto> lottoBundle;

    public LottoBundle(List<Lotto> lottoBundle) {
        this.lottoBundle = new ArrayList<>(lottoBundle);
    }

    public int countNumberOfLotto() {
        return lottoBundle.size();
    }

    public List<Lotto> getLottos() {
        return Collections.unmodifiableList(lottoBundle);
    }
}
