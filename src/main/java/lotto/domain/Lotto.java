package lotto.domain;

import lotto.exception.LottoNumberCountException;
import lotto.exception.NumberScopeException;

import java.util.*;
import java.util.stream.Collectors;

public class Lotto {
    public static final String DELIMITER = ",";
    private final List<Integer> numbers;

    private Lotto(List<Integer> numbers) {
        this.numbers = new ArrayList<>(numbers);
    }

    public static Lotto from(List<Integer> numbers) {
        return new Lotto(numbers);
    }

    public static Lotto from(String inputNumbers) {
        NullCheckNumbers(inputNumbers);
        String[] splittedNumbers = inputNumbers.split(DELIMITER);
        List<Integer> lottoNumbers = convertInputLottoNumbersToInteger(splittedNumbers);
        validateLottoNumbers(lottoNumbers);
        return new Lotto(new ArrayList<>(lottoNumbers));
    }

    private static void NullCheckNumbers(String inputNumbers) {
        if (inputNumbers == null) {
            throw new LottoNumberCountException();
        }
    }

    private static List<Integer> convertInputLottoNumbersToInteger(String[] splittedNumbers) {
        try {
            return Arrays.stream(splittedNumbers)
                    .map(String::trim)
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new LottoNumberCountException();
        }
    }

    private static void validateLottoNumbers(List<Integer> lottoNumbers) {
        validateLottoNumbersScope(lottoNumbers);
        validateLottoNumbersCount(lottoNumbers);
    }

    private static void validateLottoNumbersScope(List<Integer> lottoNumbers) {
        if (lottoNumbers.stream()
                .anyMatch(num -> num <= 0 || num > 45)) {
            throw new NumberScopeException();
        }
    }

    private static void validateLottoNumbersCount(List<Integer> lottoNumbers) {
        if (new HashSet<>(lottoNumbers).size() != 6) {
            throw new LottoNumberCountException();
        }
    }

    public boolean isContainNumber(int number) {
        return numbers.contains(number);
    }

    public long compareOtherLottoMatchCount(Lotto otherLotto) {
        List<Integer> otherLottoNumbers = otherLotto.getNumbers();

        return otherLottoNumbers.stream()
                .filter(this.numbers::contains)
                .count();
    }

    public List<Integer> getNumbers() {
        return Collections.unmodifiableList(numbers);
    }
}