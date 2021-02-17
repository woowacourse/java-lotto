package lotto.domain;

import java.util.ArrayList;
import java.util.List;

public class Lottos {
    private final int count;
    private final List<Lotto> lottos;

    public Lottos(List<Lotto> lottos) {
        this.count = lottos.size();
        this.lottos = lottos;
    }

    public Lottos(int count) {
        this.count = count;
        this.lottos = createLottos();
    }

    public List<Result> getResults(List<Integer> winningNumbers, int bonusNumber) {
        List<Result> results = new ArrayList<>();
        for (Lotto lotto : lottos) {
            int matches = lotto.countMatchingNumbers(winningNumbers);
            boolean bonusMatch = lotto.isBonusMatch(bonusNumber);
            results.add(Result.getResult(matches, bonusMatch));
        }
        return results;
    }

    private List<Lotto> createLottos() {
        LottoNumber lottoNumber = new LottoNumber();
        List<Lotto> newLottos = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            newLottos.add(new Lotto(lottoNumber.make()));
        }
        return newLottos;
    }

    public int getNumberOfLotto() {
        return lottos.size();
    }
}
