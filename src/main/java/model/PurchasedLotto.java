package model;

import java.util.List;
import model.lotto.Lotto;

public class PurchasedLotto {
    private final List<Lotto> lottos;

    public PurchasedLotto(List<Lotto> lottos) {
        this.lottos = lottos;
    }

    public Lotto findLottoByCreationOrder(int index) {
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int idx = 0; idx < lottos.size(); idx++) {
            sb.append(lottos.get(idx).getNumbers()).append("\n");
        }
        return sb.toString();
    }
}