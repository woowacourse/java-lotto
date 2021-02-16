package lotto.domain;

import java.util.ArrayList;
import java.util.List;

public class Lotto {

    private final List<Integer> numbers;

    public Lotto() {
        numbers = new ArrayList<>(LottoGenerator.generateNumbers());
    }

    public Lotto(List<Integer> selectedNumber) {
        numbers = new ArrayList<>(selectedNumber);
    }

    public LottoRank getLottoRank(List<Integer> winningNumbers, int bonusNumber) {
        int count = getMatchingCount(winningNumbers, bonusNumber);

        return LottoRank.getRank(count);
    }

    private int getMatchingCount(List<Integer> winningNumbers, int bonusNumber) {
        List<Integer> integratedWinningNumbers =
            getIntegratedWinningNumbers(winningNumbers, bonusNumber);
        int count = getCount(integratedWinningNumbers);

        if (isFirstRank(count, bonusNumber)) {
            count++;
        }

        return count;
    }

    private List<Integer> getIntegratedWinningNumbers(List<Integer> winningNumbers,
        int bonusNumber) {
        List<Integer> integratedWinningNumbers = new ArrayList<>(winningNumbers);
        integratedWinningNumbers.add(bonusNumber);
        return integratedWinningNumbers;
    }

    private int getCount(List<Integer> integratedWinningNumbers) {
        return (int) numbers.stream()
            .filter(integratedWinningNumbers::contains)
            .count();
    }

    private boolean isFirstRank(int count, int bonusNumber) {
        return count == 6 && !numbers.contains(bonusNumber);
    }
}