package model;

import java.util.List;
import model.lotto.Lotto;

public class OwnedLotto {
    private final List<Lotto> lottos;

    public OwnedLotto(List<Lotto> lottos) {
        this.lottos = lottos;
    }

    public Lotto getLottoByIndex(int index) {
        if (index < 0 || index >= lottos.size()) {
            throw new IndexOutOfBoundsException(index);
        }
        return lottos.get(index);
    }

    public boolean containByIndex(int idx, int number) {
        if (lottos.get(idx).getNumbers().contains(number)) {
            return true;
        }

        return false;
    }

    public int size() {
        return lottos.size();
    }
}