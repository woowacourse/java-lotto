package model;

import constant.ErrorMessage;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LottoNumbers {
    public static final int LOTTO_NUMBER_COUNT = 6;

    private final List<LottoNumber> numbers;

    public LottoNumbers() {
        this.numbers = generateLottoNumbers();
    }

    public LottoNumbers(List<Integer> numbers) {
        validateNumberCount(numbers);
        validateDuplicate(numbers);
        this.numbers = numbers.stream().map(LottoNumber::new).toList();
    }

    private List<LottoNumber> generateLottoNumbers() {
        Set<LottoNumber> lottoNumbers = new HashSet<>();
        while (lottoNumbers.size() < LOTTO_NUMBER_COUNT) {
            lottoNumbers.add(new LottoNumber());
        }
        return lottoNumbers.stream().sorted().toList();
    }

    public List<LottoNumber> getNumbers() {
        return numbers;
    }

    public Integer countMatchNumber(LottoNumbers otherNumbers) {
        List<LottoNumber> matchList = otherNumbers.getNumbers().stream()
                .filter(otherNumber -> numbers.stream().anyMatch((number) -> number.equals(otherNumber)))
                .toList();
        return matchList.size();
    }

    public Boolean bonusMatch(LottoNumber bonusNumber) {
        return numbers.contains(bonusNumber);
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
