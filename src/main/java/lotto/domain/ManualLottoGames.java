package lotto.domain;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ManualLottoGames implements Iterable<Lotto> {
    private final List<Lotto> lottoGames;

    public ManualLottoGames(TotalCount totalCounts) {
        this.lottoGames = new ArrayList<>();
    }

    private void addLotto(List<Number> lottoNumbers) {
        lottoGames.add(LottoGenerator.generate(lottoNumbers));
    }

    public int size() {
        return lottoGames.size();
    }

    @Override
    public Iterator<Lotto> iterator() {
        return lottoGames.iterator();
    }
}
