package repository;

import domain.Lotto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoRepository {

    private final List<Lotto> lottos;

    private LottoRepository() {
        lottos = new ArrayList<>();
    }

    public static LottoRepository create() {
        return new LottoRepository();
    }

    public void addLotto(Lotto lotto) {
        lottos.add(lotto);
    }

    public List<Lotto> getLottos() {
        return Collections.unmodifiableList(lottos);
    }
}
