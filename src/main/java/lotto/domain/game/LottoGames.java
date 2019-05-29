package lotto.domain.game;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import lotto.domain.lotto.Lotto;
import lotto.domain.lotto.Number;
import lotto.domain.util.LottoGenerator;

public abstract class LottoGames implements LottoGenerator, Iterable<Lotto> {
    private final List<Lotto> lottoGames;

    LottoGames() {
        this.lottoGames = new ArrayList<>();
    }

    List<Lotto> getLottoGames() {
        return lottoGames;
    }

    void addLotto(List<Number> numbers) {
        lottoGames.add(generate(numbers));
    }

    int size() {
        return lottoGames.size();
    }

    @Override
    public Lotto generate(List<Number> numbers) {
        return new Lotto(numbers);
    }

    @Override
    public Iterator<Lotto> iterator() {
        return lottoGames.iterator();
    }
}
