package model;

import java.util.ArrayList;
import java.util.List;

public class LottoRepository {
    private final List<Lotto> lottos = new ArrayList<>();

    public List<Lotto> getLottos() {
        return lottos;
    }

    public LottoRepository() {
    }

    public void addLotto(Lotto lotto) {
        lottos.add(lotto);
    }

}
