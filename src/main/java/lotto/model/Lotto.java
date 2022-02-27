package lotto.model;

import static java.util.Collections.*;
import static lotto.model.LottoNumber.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Lotto {
    private static final int LOTTO_SIZE = 6;
    private static final String ERROR_NOT_MATCH_LOTTO_NUMBER_SIZE = "로또 번호 개수는 6개로 입력해주세요.";
    private static final String ERROR_DUPLICATION_LOTTO_NUMBERS = "로또 번호에 중복이 존재합니다.";

    private final List<LottoNumber> lottoNumbers;

    public Lotto(List<Integer> lottoNumbers) {
        validateNumberOfLottoNumbers(lottoNumbers);
        validateDuplicationLottoNumbers(lottoNumbers);
        this.lottoNumbers = convertIntegersToLottoNumbers(lottoNumbers);
    }

    static Lotto generate() {
        List<Integer> sequentialIntegers = generateSequentialIntegers();
        shuffle(sequentialIntegers);
        List<Integer> lottoNumbers = sequentialIntegers.subList(0, LOTTO_SIZE);
        sort(lottoNumbers);
        return new Lotto(lottoNumbers);
    }

    private static List<Integer> generateSequentialIntegers() {
        return IntStream.rangeClosed(MIN_LOTTO_NUMBER, MAX_LOTTO_NUMBER)
            .boxed()
            .collect(Collectors.toList());
    }

    private void validateNumberOfLottoNumbers(List<Integer> lottoNumbers) {
        if (lottoNumbers.size() != LOTTO_SIZE) {
            throw new IllegalArgumentException(ERROR_NOT_MATCH_LOTTO_NUMBER_SIZE);
        }
    }

    private void validateDuplicationLottoNumbers(List<Integer> winningNumbers) {
        Set<Integer> distinct = new HashSet<>(winningNumbers);
        if (distinct.size() != winningNumbers.size()) {
            throw new IllegalArgumentException(ERROR_DUPLICATION_LOTTO_NUMBERS);
        }
    }

    private List<LottoNumber> convertIntegersToLottoNumbers(List<Integer> integers) {
        return integers.stream()
            .map(LottoNumber::new)
            .collect(Collectors.toUnmodifiableList());
    }

    int getMatchScore(Lotto winningNumbers) {
        return (int)lottoNumbers.stream()
            .filter(winningNumbers::isMatchNumber)
            .count();
    }

    public List<Integer> toIntegers() {
        return lottoNumbers.stream()
            .map(LottoNumber::getNumber)
            .collect(Collectors.toUnmodifiableList());
    }

    boolean isMatchNumber(LottoNumber number) {
        return lottoNumbers.contains(number);
    }
}
