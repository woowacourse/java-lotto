package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Lotto {

    public static final int LOTTO_POSSESSION_NUMBER = 6;
    public static final double BONUS_MATCHING_COUNT = 5.5;
    private final List<Integer> numbers;

    public Lotto() {
        numbers = new ArrayList<>(LottoGenerator.generateNumbers());
    }

    public Lotto(List<Integer> selectedNumber) {
        numbers = new ArrayList<>(selectedNumber);
    }

    public LottoRank getLottoRank(List<Integer> winningNumbers, int bonusNumber) {

        double count = getMatchingCount(winningNumbers, bonusNumber);

        return LottoRank.getRank(count);
    }

    private double getMatchingCount(List<Integer> winningNumbers, int bonusNumber) {
        List<Integer> integratedWinningNumbers =
                getIntegratedWinningNumbers(winningNumbers, bonusNumber);
        int count = getCount(integratedWinningNumbers);

        if (isSecondRank(count, bonusNumber)) {
            return BONUS_MATCHING_COUNT;
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

    private boolean isSecondRank(int count, int bonusNumber) {
        return count == LOTTO_POSSESSION_NUMBER && numbers.contains(bonusNumber);
    }

    public List<Integer> getNumbers() {
        return Collections.unmodifiableList(numbers);
    }
}