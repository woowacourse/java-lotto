package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
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

    private List<Lotto> createLottos() {
        LottoNumberGenerator lottoNumberGenerator = new LottoNumberGenerator();
        List<Lotto> newLottos = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            newLottos.add(new Lotto(lottoNumberGenerator.createLotto()));
        }
        return newLottos;
    }

    public List<Result> getResults(WinningLotto winningLotto) {
        List<LottoNumber> winningLottoNumbers = winningLotto.getWinningLottoNumbers();
        int bonusNumber = winningLotto.getBonusNumberAsInt();
        return collectCorrespondingResults(winningLottoNumbers, bonusNumber);
    }

    private List<Result> collectCorrespondingResults(List<LottoNumber> winningLottoNumbers, int bonusNumber) {
        List<Result> results = new ArrayList<>();
        for (Lotto lotto : lottos) {
            int matches = lotto.countMatchingNumbers(winningLottoNumbers);
            boolean bonusMatch = lotto.hasBonusNumber(bonusNumber);
            results.add(Result.getResult(matches, bonusMatch));
        }
        return results;
    }

    public List<Lotto> getLottos() {
        return Collections.unmodifiableList(lottos);
    }

    public int getNumberOfLotto() {
        return lottos.size();
    }
}
