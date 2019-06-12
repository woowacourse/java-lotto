package lotto.model;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

public class WinStat implements Iterable {
    private final Map<Rank, Integer> stat = new LinkedHashMap<>();
    private final Lottos lottoList;
    private final LottoRule rule;

    public WinStat(final Lottos lottoList, final WinningLotto winLotto, final LottoRule rule) {
        this.lottoList = lottoList;
        this.rule = rule;
        for (Rank rank : Rank.values()) {
            stat.put(rank, 0);
        }
        for (Lotto lotto : lottoList) {
            Rank key = winLotto.match(lotto);
            stat.put(key, stat.get(key) + 1);
        }
    }

    public int getTotalPrizeMoney() {
        int result = 0;
        for (Map.Entry<Rank, Integer> entry : stat.entrySet()) {
            Rank key = entry.getKey();
            int value = entry.getValue();
            result += (key.getWinningMoney() * value);
        }
        return result;
    }

    public int getAllLottoCount() {
        return lottoList.size();
    }

    public double getProfitRate() {
        double rate
                = (double) getTotalPrizeMoney()
                / (getAllLottoCount() * rule.getPrice());
        if (Double.isNaN(rate)) {
            rate = 0;
        }
        return rate;
    }

    @Override
    public Iterator<Map.Entry<Rank, Integer>> iterator() {
        return stat.entrySet().iterator();
    }
}
