package lotto.domain.game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import lotto.domain.lotto.Lotto;
import lotto.domain.lotto.Number;

public class LottoGames implements LottoGenerator, Iterable<Lotto> {
    private final List<Lotto> lottoGames;

    LottoGames() {
        this.lottoGames = new ArrayList<>();
    }

    void addLotto(List<Number> numbers) {
        lottoGames.add(generate(numbers));
    }

    int size() {
        return lottoGames.size();
    }

    public List<Lotto> combine(LottoGames lottoGames) {
        List<Lotto> result = new ArrayList<>(this.lottoGames);
        result.addAll(lottoGames.lottoGames);
        return result;
    }

    @Override
    public Lotto generate(List<Number> numbers) {
        Collections.sort(numbers);
        return new Lotto(numbers);
    }

    @Override
    public Iterator<Lotto> iterator() {
        return lottoGames.iterator();
    }
}
