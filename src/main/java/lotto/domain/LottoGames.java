package lotto.domain;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class LottoGames implements Iterable<Lotto> {
    private final List<Lotto> lottoGames;

    public LottoGames(GameCounts gameCounts) {
        this.lottoGames = new ArrayList<>();
        for (int i = 0; i < gameCounts.getGameCounts(); i++) {
            addLotto();
        }
    }

    private void addLotto() {
        this.lottoGames.add(new LottoGenerator().generate());
    }

    public int size() {
        return lottoGames.size();
    }

    @Override
    public Iterator<Lotto> iterator() {
        return lottoGames.iterator();
    }
}
