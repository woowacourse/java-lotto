package lotto.domain;

import java.util.List;

public class LottoGroup {
    private final List<Lotto> lottos;

    public LottoGroup(List<Lotto> item) {
        this.lottos = item;
    }

    public static LottoGroup from(List<Lotto> lottoList) {
        return new LottoGroup(lottoList);
    }

    public void calculateProfit(Profit profit, WinnerLotto winnerLotto) {
        for (Lotto lotto : lottos) {
            long matchCount = winnerLotto.getMatchCount(lotto);
            boolean hasBonus = winnerLotto.hasBonusNumber(lotto);
            Rank rank = Rank.find((int) matchCount, hasBonus);
            profit.incrementCount(rank);
        }
    }

    public List<List<Integer>> toIntegerLottosList() {
        return lottos.stream().map(Lotto::toIntegerList).toList();
    }

    @Override
    public String toString() {
        return "LottoGroup{" +
                "lottos=" + lottos +
                '}';
    }
}
