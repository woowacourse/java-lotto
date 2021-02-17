package lotto.domain;

import lotto.utils.LottoGenerator;

public class LottoGame {

    public Lottos buyLottos(Money money) {
        LottoGenerator lottoGenerator = new LottoGenerator();
        Lottos lottos = new Lottos();
        for (int i = 0; i < money.countLotto(); i++) {
            lottos.add(lottoGenerator.generate());
        }
        return lottos;
    }
}
