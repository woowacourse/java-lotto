package lotto.domain;

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

    public void buyLotto(List<Lotto> lottos) throws NoMoneyException {
        budget.pay(lottos.size());
        if (lottos.size() == 0 && !budget.canBuyLotto()) {
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

    public WinningResult checkWinningLotto(WinningLotto winningLotto) {
        WinningResult result = new WinningResult();
        Iterator<Lotto> iter = lottos.iterator();
        while (iter.hasNext()) {
            result.count(iter.next(), winningLotto);
        }
        return result;
    }

    public int getCountOfLottoMatch(LottoType type) {
        int count = 0;
        Iterator<Lotto> iter = lottos.iterator();
        while (iter.hasNext()) {
            count = (iter.next().matchType(type) ? ++count : count);
        }
        return count;
    }

    public void validateAffordability(int countOfManualLotto) {
        if (!budget.canBuyLotto(countOfManualLotto)) {
            throw new InvalidNumberException("구입 금액으로 " + countOfManualLotto + "장의 로또를 살 수 없습니다.");
        }
    }
}