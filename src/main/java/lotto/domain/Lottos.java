package lotto.domain;

import java.util.ArrayList;
import java.util.List;

public class Lottos {

    private List<Lotto> lottos = new ArrayList<>();

    public Lottos(int numberOfLotto) {
        for (int i = 0; i < numberOfLotto; i++) {
            lottos.add(new Lotto(RandomNumberGenerator.generate()));
        }
    }

    public Lottos(List<Lotto> lottos) {
        this.lottos = new ArrayList<>(lottos);
    }

    public List<Lotto> getList() {
        return lottos;
    }
}
