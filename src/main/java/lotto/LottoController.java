package lotto;

import lotto.LottoView;

public class LottoController {
    private static Lottos lottos;

    public void generateLottos() {
        lottos = new Lottos(LottoView.requestMoney());
        LottoView.buyLotto(lottos.getCount());
    }

}
