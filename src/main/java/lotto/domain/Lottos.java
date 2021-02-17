package lotto.domain;

import java.util.ArrayList;
import java.util.List;

public class Lottos {
    private final int count;
    private final List<Lotto> lottos;

    public Lottos(List<Lotto> lottos) {
        this.lottos = lottos;
    }

    private List<Lotto> createLottos() {
        LottoNumber lottoNumber = new LottoNumber();
        List<Lotto> newLottos = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            newLottos.add(new Lotto(lottoNumber.make()));
        }
        return newLottos;
    }

    public List<Result> getResults(List<Integer> winningNumbers, int bonusNumber) {
        List<Result> results = new ArrayList<>();
        for (Lotto lotto : lottos) {
            int matches = lotto.countMatchingNumbers(winningNumbers);
            boolean bonusMatch = lotto.isBonusMatch(bonusNumber);

            if (matches == Result.SECOND.getCount() && bonusMatch) {
                results.add(Result.SECOND);
                continue;
            }

            for (Result result : Result.values()){
                if(matches == result.getCount()){
                    results.add(result);
                }
            }

            if (matches < Result.FIFTH.getCount()) {
                results.add(Result.NONE);
            }
        }
        return results;
    }
}
