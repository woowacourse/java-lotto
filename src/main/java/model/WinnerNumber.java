package model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WinnerNumber {

    private final List<Integer> winnerNumbers;
    private final int bonusBall;

    public WinnerNumber(List<Integer> winnerNumbers, int bonusBall) {
        this.winnerNumbers = winnerNumbers;
        this.bonusBall = bonusBall;
    }

    public LottoCalculator compareLottoToWinning(Lottos lottos) {
        LottoCalculator lottoCalculator = new LottoCalculator();
        List<Lotto> compare = lottos.getLottos();
        for (Lotto lotto : compare) {
            compareSingleLottoToWinning(lottoCalculator, lotto);
        }
        return lottoCalculator;
    }

    private void compareSingleLottoToWinning(LottoCalculator lottoCalculator, Lotto lotto) {
        List<Integer> lottoNumber = lotto.getLottoNumber();

        Set<Integer> winNumber = new HashSet<>(winnerNumbers);
        Set<Integer> lottoNumbers = new HashSet<>(lottoNumber);

        lottoNumbers.retainAll(winNumber);
        boolean isBonus = lottoNumber.contains(bonusBall);
        lottoCalculator.compareWinning(lottoNumbers.size(), isBonus);
    }
}
