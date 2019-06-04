package lotto.domain.lotto;

import lotto.domain.lottogenerator.LottoGeneratingStrategy;
import lotto.domain.lottogenerator.LottoGenerator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoRepository {
    private List<Lotto> lottos;

    public LottoRepository() {
        this.lottos = new ArrayList<>();
    }

    public void register(LottoGeneratingStrategy strategy) {
        lottos.add(LottoGenerator.create(strategy));
    }

    public List<Lotto> getLottos() {
        return Collections.unmodifiableList(lottos);
    }
}
