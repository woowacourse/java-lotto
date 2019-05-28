package lotto.domain;

import java.util.ArrayList;
import java.util.List;

public class LottoGames {
    private final List<Lotto> lottoGames;

    public LottoGames(GameCounts gameCounts) {
        this.lottoGames = new ArrayList<>();
        for (int i = 0; i < gameCounts.getGameCounts(); i++) {
            addLotto();
        }
    }

    private void addLotto() {
        this.lottoGames.add(LottoGenerator.generate());
    }

    public int size() {
        return lottoGames.size();
    }
}
