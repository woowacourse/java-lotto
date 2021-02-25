package domain.lottoGame;

import java.util.*;

public class Lottos {

    private final List<Lotto> lottos;

    public Lottos() {
        this.lottos = new ArrayList<>();
    }

    public Lottos(final List<Lotto> lottos) {
        this.lottos = new ArrayList<>(lottos);
    }

    public LottoWinningTable checkCorrect(WinningLotto winningLotto) {
        Map<LottoRank, Long> results = intializeMap();

        for (Lotto lotto : lottos) {
            LottoRank winningRank = LottoRank.valueOf(winningLotto, lotto);
            results.put(winningRank, results.get(winningRank) + 1);
        }

        return new LottoWinningTable(results);
    }

    private Map<LottoRank, Long> intializeMap() {
        Map<LottoRank, Long> results = new HashMap<>();

        Arrays.stream(LottoRank.values())
                .forEach(rank -> results.put(rank, 0L));

        return results;
    }

    public List<Lotto> getLottos() {
        return new ArrayList<>(lottos);
    }

    public int getNumberOfLotto() {
        return lottos.size();
    }

    public Lottos add(Lottos lottos) {
        List<Lotto> oldLottos = new ArrayList<>(this.lottos);
        oldLottos.addAll(lottos.lottos);
        return new Lottos(oldLottos);
    }
}