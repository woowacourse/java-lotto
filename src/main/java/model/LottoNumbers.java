package model;

import constant.ErrorMessage;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class LottoNumbers {
    public static final int LOTTO_NUMBER_COUNT = 6;
    public static final int MINIMUM_LOTTO_NUMBER = 1;
    public static final int MAXIMUM_LOTTO_NUMBER = 45;
    private final List<Integer> numbers;

    public LottoNumbers() {
        this.numbers = generateLottoNumbers();
    }

    public LottoNumbers(List<Integer> numbers) {
        numbers.forEach(this::validateBound);
        validateNumberCount(numbers);
        validateDuplicate(numbers);
        this.numbers = numbers;
    }

    private List<Integer> generateLottoNumbers() {
        Set<Integer> lottoNumbers = new HashSet<>();
        while (lottoNumbers.size() < LOTTO_NUMBER_COUNT) {
            Random random = new Random();
            int randomNumber = random.nextInt(MAXIMUM_LOTTO_NUMBER) + 1;
            lottoNumbers.add(randomNumber);
        }
        return lottoNumbers.stream().sorted().toList();
    }

    public List<Integer> getNumbers() {
        return numbers;
    }

    public Integer countMatchNumber(LottoNumbers lottoNumbers) {
        List<Integer> matchList = lottoNumbers.getNumbers().stream()
                .filter(lottoNumber -> numbers.stream().anyMatch((number) -> number == lottoNumber))
                .toList();
        return matchList.size();
    }

    public Boolean bonusMatch(Integer bonusNumber) {
        return numbers.contains(bonusNumber);
    }

    private void validateBound(Integer input) {
        if (input < MINIMUM_LOTTO_NUMBER || input > MAXIMUM_LOTTO_NUMBER) {
            throw new IllegalArgumentException(ErrorMessage.NUMBER_BOUND_EXCEPTION);
        }
    }

    private void validateNumberCount(List<Integer> winNumbers) {
        if (winNumbers.size() != LOTTO_NUMBER_COUNT) {
            throw new IllegalArgumentException(ErrorMessage.NUMBER_COUNT_EXCEPTION);
        }
    }

    private void validateDuplicate(List<Integer> numbers) {
        Set<Integer> numberSet = new HashSet<>(numbers);
        if (numbers.size() != numberSet.size()) {
            throw new IllegalArgumentException(ErrorMessage.NUMBER_DUPLICATE_EXCEPTION);
        }
    }
}
