package lotto.domain;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

class LottoContainer {
    private List<Lotto> lottos;

    LottoContainer(List<Lotto> lottos) {
        this.lottos = lottos;
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