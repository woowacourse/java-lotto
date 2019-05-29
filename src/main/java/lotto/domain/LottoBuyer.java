package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class LottoBuyer {
    private Budget budget;
    private LottoContainer lottos;

    public LottoBuyer(Budget budget) {
        this.budget = budget;
        lottos = new LottoContainer(Collections.EMPTY_LIST);
    }

    public void buyLotto() throws NoMoneyException {
        List<Lotto> lottos = new ArrayList<>();
        if (!budget.canBuyLotto()) {
            throw new NoMoneyException("로또를 살 돈이 부족합니다.");
        }
        while (budget.canBuyLotto()) {
            budget.pay();
            lottos.add(LottoSeller.createLotto());
        }
        this.lottos = new LottoContainer(lottos);
    }

    public List<String> showLottos() {
        return lottos.showLottos();
    }

    public WinningResult checkWinningLotto(Lotto winningLotto) {
        WinningResult result = new WinningResult();
        Iterator<Lotto> iter = lottos.iterator();
        while (iter.hasNext()) {
            result.count(iter.next(), winningLotto);
        }
        return result;
    }
}