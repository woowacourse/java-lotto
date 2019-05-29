package lotto.domain;

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
}