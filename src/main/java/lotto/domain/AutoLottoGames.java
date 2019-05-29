package lotto.domain;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class AutoLottoGames implements Iterable<Lotto> {
    private static final int FIRST_LOTTO = 0;

    private final List<Lotto> lottoGames;

    public AutoLottoGames(AutoCount autoCount) {
        this.lottoGames = new ArrayList<>();
        for (int i = FIRST_LOTTO; i < autoCount.getCount(); i++) {
            addLotto();
        }
    }

    public List<Lotto> getLottoGames() {
        return lottoGames;
    }

    private void addLotto() {
        lottoGames.add(new LottoGenerator().generate());
    }

    public int size() {
        return lottoGames.size();
    }

    @Override
    public Iterator<Lotto> iterator() {
        return lottoGames.iterator();
    }
}
