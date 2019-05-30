package lotto.domain;

import java.util.ArrayList;
import java.util.List;

public class Lottos {

    private List<Lotto> lottos;

    public Lottos(List<Lotto> manualLottos, int numberOfAutoLotto) {
        lottos = new ArrayList<>(manualLottos);
        for (int i = 0; i < numberOfAutoLotto; i++) {
            lottos.add(new Lotto(RandomNumberGenerator.generate()));
        }
    }

    public Lottos(List<Lotto> lottos) {
        this.lottos = new ArrayList<>(lottos);
    }

    public int getSize() {
        return lottos.size();
    }

    public List<Lotto> getList() {
        return lottos;
    }
}
