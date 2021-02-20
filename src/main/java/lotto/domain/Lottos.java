package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Lottos {
    //TODO:
    // final로 만들고 밑에 생성자 관련해서 리팩토링
    private final List<Lotto> lottos;

    public Lottos(List<Lotto> lottos) {
        this.lottos = lottos;
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
