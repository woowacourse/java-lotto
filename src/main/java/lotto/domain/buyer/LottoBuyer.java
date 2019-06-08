package lotto.domain.buyer;

import lotto.domain.WinningResult;
import lotto.domain.lotto.Lotto;
import lotto.domain.lotto.LottoType;
import lotto.domain.lotto.WinningLotto;

import java.util.Iterator;
import java.util.List;

public class LottoBuyer {
    private static final String NO_MONEY_ERROR_MSG = "구입 금액으로 원하는 장 수의 로또를 살 수 없습니다.";

    private Budget budget;
    private LottoContainer lottos;

    public LottoBuyer(Budget budget) {
        this.budget = budget;
        lottos = new LottoContainer();
    }

    public void buyManualLotto(List<Lotto> lottos) {
        budget.pay(lottos.size());
        this.lottos.addLotto(lottos);
    }

    public void buyAutoLotto() {
        while (budget.canBuyLotto()) {
            budget.pay();
            lottos.addLotto(Lotto.of());
        }
    }

    public List<String> showLottos() {
        return lottos.showLottos();
    }

    public WinningResult checkWinningLotto(WinningLotto winningLotto) {
        return new WinningResult(lottos.iterator(), winningLotto);
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
            throw new NoMoneyException(NO_MONEY_ERROR_MSG);
        }
    }
}