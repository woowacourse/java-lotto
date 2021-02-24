package lottogame.domain.lotto;

import lottogame.domain.Rank;
import lottogame.domain.stats.LottoResults;

import java.util.*;

public class Lottos {
    private final List<Lotto> lottos = new ArrayList<>();

    public Lottos(List<Lotto> values) {
        lottos.addAll(values);
    }

    public List<Lotto> values() {
        return new ArrayList<>(lottos);
    }

    public LottoResults matchLottos(WinningLotto winningLotto) {
        Map<Rank, Integer> results = new EnumMap<>(Rank.class);
        Arrays.stream(Rank.values()).forEach(rank -> results.put(rank, 0));
        for (Lotto lotto : lottos) {
            int count = lotto.match(winningLotto.values());
            boolean bonus = lotto.containsBonus(winningLotto);
            Rank rank = Rank.of(count, bonus);
            results.put(rank, results.get(rank) + 1);
        }
        return new LottoResults(results);
    }

    public int size() {
        return lottos.size();
    }

    public void add(List<String> lottoNumbers) {
        List<Lotto> manualLottos = new ArrayList<>();
        for (String lottoNumber : lottoNumbers) {
            manualLottos.add(Lotto.of(lottoNumber));
        }
        lottos.addAll(0, manualLottos);
    }
}
