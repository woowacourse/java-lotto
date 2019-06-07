package lotto.domain.buyer;

import lotto.domain.lotto.Lotto;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

class LottoContainer {
    private List<Lotto> lottos;

    LottoContainer() {
        lottos = new ArrayList<>();
    }

    void addLotto(List<Lotto> lottos) {
        this.lottos.addAll(lottos);
    }

    void addLotto(Lotto lotto) {
        this.lottos.add(lotto);
    }

    List<String> showLottos() {
        return lottos.stream().map(Lotto::toString).collect(Collectors.toList());
    }

    Iterator<Lotto> iterator() {
        return new Iterator<Lotto>() {
            int count = 0;

            @Override
            public boolean hasNext() {
                return count < lottos.size();
            }

            @Override
            public Lotto next() {
                return lottos.get(count++);
            }
        };
    }
}