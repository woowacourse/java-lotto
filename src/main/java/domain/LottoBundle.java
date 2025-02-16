package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoBundle {
    private final List<Lotto> lottoBundle = new ArrayList<>();

    private LottoBundle() {
    }

    public static LottoBundle create() {
        return new LottoBundle();
    }

    public void addLotto(Lotto lotto) {
        lottoBundle.add(lotto);
    }

    public List<Lotto> getLottoBundle() {
        return Collections.unmodifiableList(lottoBundle);
    }
}
