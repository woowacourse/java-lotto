package lotto.domain.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WinningLotto {

    private Lotto winningLotto;
    private Number bonusNumber;

    public WinningLotto(Lotto winningNumbers, Number bonusNumber) {
        this.winningLotto = winningNumbers;
        this.bonusNumber = bonusNumber;
    }

    public int matchCount(Lotto purchasedLotto) {
        List<Number> myLotto = new ArrayList<>(purchasedLotto.getLotto());
        List<Number> winLotto = new ArrayList<>(winningLotto.getLotto());
        myLotto.retainAll(winLotto);
        return myLotto.size();
    }

    public boolean matchBonusNumber(Lotto purchasedLotto) {
        return purchasedLotto.getLotto().contains(bonusNumber);
    }

    public List<Rank> match(PurchasedLotto purchasedLottos) {
        List<Rank> ranks = new ArrayList<>();

        for (Lotto lotto : purchasedLottos.getLotto()) {
            ranks.add(Rank.valueOf(matchCount(lotto), matchBonusNumber(lotto)));
        }
        return ranks;
    }

    public Map<Rank, Integer> calculatePrize(List<Rank> ranks) {
        Map<Rank, Integer> winResult = new HashMap<>();
        for (Rank rank : Rank.values()) {
            winResult.put(rank, 0);
        }

        for (Rank rank : ranks) {
            winResult.put(rank, (winResult.get(rank) + rank.getPrize()));
        }
        return winResult;
    }
}
