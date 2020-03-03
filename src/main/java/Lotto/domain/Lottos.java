package Lotto.domain;

import java.util.List;
import java.util.stream.Collectors;

public class Lottos {
    private static final String NEW_LINE = "\n";

    private List<Lotto> lottos;

    public Lottos(List<Lotto> lottos) {
        this.lottos = lottos;
    }

    public String getLottosInOneLine() {
        return lottos.stream()
                .map(Lotto::getLotto)
                .collect(Collectors.joining(NEW_LINE));
    }

    List<Lotto> getLottos() {
        return this.lottos;
    }
}
