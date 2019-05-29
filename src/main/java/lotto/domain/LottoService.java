package lotto.domain;

import java.util.ArrayList;
import java.util.List;

class LottoService {
    private LottoMachine lottoMachine;
    private final List<Lotto> lottos;

    LottoService(final int money) {
        lottoMachine = new LottoMachine(money);
        lottos = new ArrayList<>();
    }

    void buyLotto(final List<Integer> numbers) {
        lottos.add(lottoMachine.buy(numbers));
    }

    boolean canBuy() {
        return lottoMachine.isRemainMoney();
    }

    LottoGameResult result(final WinningLotto winningLotto) {
        return LottoGameResult.of(lottos, winningLotto);
    }
}
