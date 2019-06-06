package lotto.domain.lotto;

import lotto.domain.lottogenerator.LottoGeneratingStrategy;
import lotto.domain.lottogenerator.LottoGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class LottoRepository {
    private List<Lotto> lottos;

    public LottoRepository() {
        this.lottos = new ArrayList<>();
    }

    public void register(LottoGeneratingStrategy strategy) {
        if (Objects.isNull(strategy)) {
            throw new NullPointerException();
        }
        lottos.add(LottoGenerator.create(strategy));
    }

    public LottoTickets createLottoTickets() {
        return new LottoTickets(lottos);
    }
}
