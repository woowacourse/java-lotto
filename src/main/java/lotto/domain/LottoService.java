package lotto.domain;

import java.util.List;

public class LottoService {
    private final LottoMachine lottoMachine;
    private final Lottos lottos;

    public LottoService() {
        lottoMachine = new LottoMachine();
        lottos = new Lottos();
    }

    public void charge(final int money) {
        lottoMachine.charge(money);
    }

    public void buy(final Lotto lotto) {
        lottoMachine.buy();
        lottos.add(lotto);
    }

    public boolean canBuy() {
        return lottoMachine.isRemainMoney();
    }

    public LottoGameResult gameResult() {
        return LottoGameResult.of(lottos.getLottos());
    }

    public List<Lotto> getLottos() {
        return lottos.getLottos();
    }
}
