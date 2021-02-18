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

    public List<Lotto> getLottos() {
        return lottos;
    }

    public List<Result> getResults(WinningLotto winningLotto) {
        List<Integer> winningLottoNumbers = winningLotto.getWinningLottoNumbers();
        int bonusNumber = winningLotto.getBonusNumber();
        return collectCorrespondingResults(winningLottoNumbers, bonusNumber);
    }

    private List<Result> collectCorrespondingResults(List<Integer> winningLottoNumbers, int bonusNumber) {
        List<Result> results = new ArrayList<>();
        for (Lotto lotto : lottos) {
            int matches = lotto.countMatchingNumbers(winningLottoNumbers);
            boolean bonusMatch = lotto.hasBonusNumber(bonusNumber);
            results.add(Result.getResult(matches, bonusMatch));
        }
        return results;
    }

    private List<Lotto> createLottos() {
        LottoNumberGenerator lottoNumberGenerator = new LottoNumberGenerator();
        List<Lotto> newLottos = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            newLottos.add(new Lotto(lottoNumberGenerator.make()));
        }
        return newLottos;
    }

    public int getNumberOfLotto() {
        return lottos.size();
    }
}
