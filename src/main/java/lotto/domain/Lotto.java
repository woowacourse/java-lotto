package lotto.domain;

import lotto.exception.WinningNumberException;

import java.util.*;
import java.util.stream.Collectors;

public class Lotto {

    public static final int LOTTO_POSSESSION_NUMBER = 6;
    public static final double BONUS_MATCHING_COUNT = 5.5;
    public static final String DELIMITER = ",";

    private final List<Integer> numbers;

    public Lotto() {
        numbers = new ArrayList<>(LottoGenerator.generateNumbers());
    }

    public Lotto(String inputNumbers) {
        NullCheckNumbers(inputNumbers);
        String[] splittedNumbers = inputNumbers.split(DELIMITER);
        List<Integer> winningNumbers = convertInputWinningNumbersToInteger(splittedNumbers);
        validateWinningNumbersCount(winningNumbers);
        this.numbers = new ArrayList<>(winningNumbers);
    }


    private static void validateWinningNumbersCount(List<Integer> winningNumbers) {
        if (new HashSet<>(winningNumbers).size() != 6) {
            throw new WinningNumberException();
        }
    }

    private static List<Integer> convertInputWinningNumbersToInteger(String[] splittedNumbers) {
        try {
            return Arrays.stream(splittedNumbers)
                    .map(String::trim)
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new WinningNumberException();
        }
    }

    private static void NullCheckNumbers(String inputNumbers) {
        if (inputNumbers == null) {
            throw new WinningNumberException();
        }
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