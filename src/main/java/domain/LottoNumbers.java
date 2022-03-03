package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class LottoNumbers {

    public static final int LOTTO_NUMBERS_SIZE = 6;
    private static final String LOTTO_NUMBER_SIZE_NOT_VALID = "로또 번호는 6자리여야 합니다.";
    private static final String LOTTO_NUMBER_DUPLICATE = "로또 번호는 중복되지 않아야 합니다.";

    private final List<LottoNumber> numbers;

    public LottoNumbers(List<LottoNumber> lottoNumbers) {
        validateSize(lottoNumbers);
        validateDuplicateLottoNumber(lottoNumbers);
        Collections.sort(lottoNumbers);
        numbers = lottoNumbers;
    }

    public static LottoNumbers convertToLottoNumber(List<Integer> lottoNumbers) {
        return new LottoNumbers(lottoNumbers.stream()
                .map(LottoNumber::getInstance)
                .collect(Collectors.toList()));
    }

    public int countDuplicateNumbers(LottoNumbers lottoNumbers) {
        List<LottoNumber> duplicatedLottoNumbers = new ArrayList<>(numbers);
        duplicatedLottoNumbers.retainAll(lottoNumbers.numbers);
        return duplicatedLottoNumbers.size();
    }

    public boolean contains(LottoNumber number) {
        return numbers.contains(number);
    }

    private void validateSize(List<LottoNumber> lottoNumbers) {
        if (lottoNumbers.size() != LOTTO_NUMBERS_SIZE) {
            throw new IllegalArgumentException(LOTTO_NUMBER_SIZE_NOT_VALID);
        }
    }

    private void validateDuplicateLottoNumber(List<LottoNumber> lottoNumbers) {
        long size = lottoNumbers.stream()
                .distinct()
                .count();

        if (size != LOTTO_NUMBERS_SIZE) {
            throw new IllegalArgumentException(LOTTO_NUMBER_DUPLICATE);
        }
    }

    public List<LottoNumber> getLottoNumbers() {
        return Collections.unmodifiableList(numbers);
    }
}
