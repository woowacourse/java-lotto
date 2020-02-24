package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class LottoBundle {
    private final List<Lotto> lottoBundle = new ArrayList<>();

    public LottoBundle() {
    }

    public void addLottoBundle(final List<Lotto> targetLottoBundle) {
        Objects.requireNonNull(targetLottoBundle);
        lottoBundle.addAll(targetLottoBundle);
    }

    public void addLotto(final Lotto lotto) {
        lottoBundle.add(lotto);
    }

    public List<Lotto> getLottoBundle() {
        return Collections.unmodifiableList(lottoBundle);
    }
}