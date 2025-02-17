package model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WinnerNumber {

    private final List<Integer> winnerNumbers;
    private final int bonusBall;

    public WinnerNumber(List<Integer> winnerNumbers, int bonusBall) {
        validateDuplicateValue(winnerNumbers);
        validateDuplicateBonusBall(winnerNumbers, bonusBall);
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

    private void validateDuplicateValue(List<Integer> winnerNumbers) {
        Set<Integer> duplicateCheck = new HashSet<>(winnerNumbers);
        if (duplicateCheck.size() != winnerNumbers.size()) {
            throw new IllegalArgumentException("중복된 값을 입력해서는 안됩니다.");
        }
    }

    private void validateDuplicateBonusBall(List<Integer> winnerNumbers, int bonusBall) {
        if (winnerNumbers.contains(bonusBall)) {
            throw new IllegalArgumentException("중복된 값을 입력해서는 안됩니다.");
        }
    }

    private boolean isBonus(List<Integer> lottoNumber) {
        boolean isBonus = false;
        if (lottoNumber.size() == 5){
            isBonus = lottoNumber.contains(bonusBall);
        }
        return isBonus;
    }
}
