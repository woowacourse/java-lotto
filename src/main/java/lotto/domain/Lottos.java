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

    public List<Rank> getResults(WinningLotto winningLotto) {
        List<LottoNumber> winningLottoNumbers = winningLotto.getWinningLottoNumbers();
        int bonusNumber = winningLotto.getBonusNumberAsInt();
        return collectCorrespondingResults(winningLottoNumbers, bonusNumber);
    }

    private List<Rank> collectCorrespondingResults(List<LottoNumber> winningLottoNumbers, int bonusNumber) {
        List<Rank> ranks = new ArrayList<>();
        for (Lotto lotto : lottos) {
            int matches = lotto.countMatchingNumbers(winningLottoNumbers);
            boolean bonusMatch = lotto.hasBonusNumber(bonusNumber);
            ranks.add(Rank.getCorrespondingRank(matches, bonusMatch));
        }
        return ranks;
    }

    public List<Lotto> getLottos() {
        return Collections.unmodifiableList(lottos);
    }

    public int getNumberOfLotto() {
        return lottos.size();
    }
}
