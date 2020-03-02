package lotto.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Lottos {
    public static final int PRICE_PER_LOTTO = 1_000;

    private List<Lotto> lottos;

    public Lottos() { this.lottos = new ArrayList<>(); }

    public Lottos(List<Lotto> lottos) {
        this.lottos = new ArrayList(lottos);
    }

    public List<Lotto> getLottos() {
        return this.lottos;
    }

    public int getLottosSize() { return this.lottos.size(); }

    public Lottos addLottos(Lottos lottosToAdd) {
        this.lottos.addAll(lottosToAdd.getLottos());
        return new Lottos(lottos);
    }

    public Set<LottoNumber> getLottoNumbers(int index) {
        return this.lottos.get(index).getLottoNumbers();
    }
}
