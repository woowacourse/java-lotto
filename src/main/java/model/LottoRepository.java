package model;

import java.util.ArrayList;
import java.util.List;

public class LottoRepository {
    private final List<List<Lotto>> lottos = new ArrayList<>();

    public LottoRepository() {
    }

    public void addLotto(ArrayList<Lotto> lotto) {
        lottos.add(lotto);
    }

}
