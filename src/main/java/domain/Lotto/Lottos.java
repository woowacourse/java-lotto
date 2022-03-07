package domain.Lotto;

import domain.Rank;
import domain.player.Money;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Lottos {

    private final List<Lotto> lottos;

    public Lottos(List<Lotto> lottos) {
        this.lottos = lottos;
    }

    public List<Rank> judgeAll(WinningLotto winningLotto) {
        List<Rank> results = new ArrayList<>();
        for (Lotto lotto : lottos) {
            results.add(lotto.compare(winningLotto));
        }
        return results;
    }

    public double calculateIncomeRate(double totalIncome) {
        return totalIncome / (lottos.size() * Money.LOTTO_PRICE);
    }

    public List<Lotto> getLottos() {
        return Collections.unmodifiableList(lottos);
    }
}
