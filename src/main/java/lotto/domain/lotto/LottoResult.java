package lotto.domain.lotto;

import static lotto.domain.lotto.LottoRank.checkRank;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.Map;

public class LottoResult {

    private final Map<LottoRank, Integer> rank;

    public LottoResult() {
        this.rank = new EnumMap<>(LottoRank.class);
        Arrays.stream(LottoRank.values()).forEach(level -> rank.put(level, 0));
    }

    public Map getResult() {
        return this.rank;
    }

    public void add(Lotto lotto, WinningLotto winningLotto) {
        int count = (int) lotto.getNumbers().stream()
            .filter(number -> winningLotto.getLotto().getNumbers().contains(number)).count();
        boolean bonus = lotto.getNumbers().contains(winningLotto.getBonus());
        LottoRank lottoRank = findRank(count, bonus);
        rank.put(lottoRank, rank.get(lottoRank) + 1);
    }

    public LottoRank findRank(int count, boolean bonus) {
        return LottoRank.checkRank(count, bonus);
    }


}
