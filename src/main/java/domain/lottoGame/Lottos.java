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
        Map<LottoRank, Long> results = new HashMap<>();

        for (LottoRank rank : LottoRank.values()) {
            results.put(rank, 0L);
        }

        for (Lotto lotto : lottos) {
            LottoRank winningRank = LottoRank.valueOf(winningLotto, lotto);
            results.put(winningRank, results.get(winningRank) + 1);
        }
        return new LottoWinningTable(results);
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