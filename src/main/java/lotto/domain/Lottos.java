package lotto.domain;

import java.util.List;

public class Lottos implements LottoTickets {
    private final List<Lotto> lottos;

    public Lottos(final List<Lotto> lottos) {
        this.lottos = lottos;
    }

    @Override
    public int size() {
        return lottos.size();
    }

    @Override
    public Lotto get(int index) {
        return lottos.get(index);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Lotto lotto : lottos) {
            sb.append("[").append(lotto.toString()).append("]\n");
        }
        return sb.toString();
    }
}
