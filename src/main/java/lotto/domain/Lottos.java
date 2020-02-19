package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Lottos {
    private final List<Lotto> lottos;

    public Lottos(int lottoCount) {
        List<Lotto> lottoLines = new ArrayList<Lotto>();
        for (int i = 0; i < lottoCount; i++) {
            lottoLines.add(LottoGenerator.generate());
        }
        this.lottos = lottoLines;
    }

    public List<Lotto> getLottos() {
        return Collections.unmodifiableList(lottos);
    }
}
