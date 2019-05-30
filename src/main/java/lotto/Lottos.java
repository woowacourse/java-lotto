package lotto;

import java.util.List;

public class Lottos {
    private List<Lotto> lottos;

    public Lottos(int lottoQuantity, LottoCreator creator) {
        this.lottos = creator.createLottos(lottoQuantity);
    }

    public List<Lotto> getLottos() {
        return lottos;
    }
}
