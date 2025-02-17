package Model;

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

    public void compareWinning(Lotto lotto) {
        List<Integer> lottoNumber = lotto.getLottoNumber();
        Set<Integer> winNumber = new HashSet<>(winnerNumbers);
        Set<Integer> lottoNumbers = new HashSet<>(lottoNumber);
        lottoNumbers.retainAll(winNumber);
        boolean bonusBall = isBonus(lottoNumber);
        LottoResult.addCount(lottoNumbers.size(), bonusBall);
    }

    private boolean isBonus(List<Integer> lottoNumber) {
        boolean isBonus = false;
        if (lottoNumber.size() == 5){
            isBonus = lottoNumber.contains(bonusBall);
        }
        return isBonus;
    }
}
