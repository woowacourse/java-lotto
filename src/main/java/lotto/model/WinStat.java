package lotto.model;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class WinStat implements Iterable {
    private final Map<Rank, Integer> stat = new LinkedHashMap<>();
    private final List<Lotto> lottoList;
    private final LottoRule rule;

    public WinStat(final List<Lotto> lottoList, final WinningLotto winLotto, final LottoRule rule) {
        this.lottoList = lottoList;
        this.rule = rule;
        for (Rank rank : Rank.values()) { // 자료구조 초기화
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
                = (double) getTotalPrizeMoney() // 소수점 형변환
                / (getAllLottoCount() * rule.getPrice());
        if (Double.isNaN(rate)) { // 0으로 나뉠 경우 대비
            rate = 0;
        }
        return rate;
    }

    @Override
    public Iterator<Map.Entry<Rank, Integer>> iterator() {
        return stat.entrySet().iterator();
    }
}
