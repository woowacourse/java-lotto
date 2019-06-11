package lotto.domain;

import java.util.ArrayList;
import java.util.List;

public class Lottos {
    //TODO 안쓰니까 지우기
    private final List<Lotto> lottos;

    public Lottos() {
        lottos = new ArrayList<>();
    }

    void add(final Lotto lotto) {
        lottos.add(lotto);
    }

    public List<Lotto> getLottos() {
        return new ArrayList<>(lottos);
    }
}
