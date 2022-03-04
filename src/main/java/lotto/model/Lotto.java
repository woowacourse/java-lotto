package lotto.model;

import java.util.Collections;
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
        Collections.sort(lottoNumbers);
        this.lottoNumbers = convertIntegersToLottoNumbers(lottoNumbers);
    }

    private void validateNumberOfLottoNumbers(List<Integer> lottoNumbers) {
        if (lottoNumbers.size() != LOTTO_SIZE) {
            throw new IllegalArgumentException(ERROR_NOT_MATCH_LOTTO_NUMBER_SIZE);
        }
    }

    private void validateDuplicationLottoNumbers(List<Integer> lottoNumbers) {
        Set<Integer> distinct = new HashSet<>(lottoNumbers);
        if (distinct.size() != lottoNumbers.size()) {
            throw new IllegalArgumentException(ERROR_DUPLICATION_LOTTO_NUMBERS);
        }
    }

    private List<LottoNumber> convertIntegersToLottoNumbers(List<Integer> integers) {
        return integers.stream()
                .map(LottoNumber::new)
                .collect(Collectors.toList());
    }

    public static Lotto generateAuto() {
        List<Integer> lottoNumbers = generateSequentialIntegers();
        Collections.shuffle(lottoNumbers);

        return new Lotto(lottoNumbers.subList(0, LOTTO_SIZE));
    }

    private static List<Integer> generateSequentialIntegers() {
        return IntStream.rangeClosed(LottoNumber.MIN, LottoNumber.MAX)
                .boxed()
                .collect(Collectors.toList());
    }

    public int matchWinningNumbers(WinningNumbers winningNumbers) {
        return (int) lottoNumbers.stream()
                .filter(winningNumbers::isNumberMatch)
                .count();
    }

    public List<Integer> toIntegers() {
        return lottoNumbers.stream()
                .map(LottoNumber::getNumber)
                .collect(Collectors.toList());
    }

    public boolean isNumberMatch(LottoNumber number) {
        return lottoNumbers.contains(number);
    }
}
