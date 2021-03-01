package lotto.domain;

import lotto.util.LottoGenerator;

import java.util.ArrayList;
import java.util.List;

public class Lottos {

    private final List<Lotto> lottos;

    public Lottos() {
        this.lottos = new ArrayList<>();
    }

    public void buyLotto(LottoGenerator lottoGenerator, String manualLotto) {
        this.lottos.add(lottoGenerator.generate(manualLotto));
    }

    public void buyLotto(LottoGenerator lottoGenerator) {
        this.lottos.add(lottoGenerator.generate());
    }

    public List<Lotto> getLottos() {
        return lottos;
    }
}
