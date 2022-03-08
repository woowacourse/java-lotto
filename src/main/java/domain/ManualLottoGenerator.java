package domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ManualLottoGenerator implements LottoGenerator {

    private static final int LOTTO_NUMBER_DIGIT = 6;
    private static final String LOTTO_NUMBER_DIGIT_EXCEPTION = "[ERROR] 로또 번호는 6자리입니다.";
    private static final String LOTTO_NUMBER_DUPLICATED_EXCEPTION = "[ERROR] 로또 번호는 중복될 수 없습니다.";

    private final List<LottoNumber> lottoNumbers;

    public ManualLottoGenerator(List<Integer> rawLottoNumber) {
        validate(new ArrayList<>(rawLottoNumber));
        this.lottoNumbers = rawLottoNumber.stream()
                .map(LottoNumber::valueOf)
                .collect(Collectors.toList());
    }

    @Override
    public List<LottoNumber> generateLottoNumber() {
        return lottoNumbers;
    }

    private void validate(List<Integer> rawLottoNumber) {
        validateLength(new ArrayList<>(rawLottoNumber));
        validateDuplication(new ArrayList<>(rawLottoNumber));
    }

    private void validateLength(List<Integer> numbers) {
        if (numbers.size() != LOTTO_NUMBER_DIGIT) {
            throw new IllegalArgumentException(LOTTO_NUMBER_DIGIT_EXCEPTION);
        }
    }

    private void validateDuplication(List<Integer> numbers) {
        Set<Integer> noDuplicatedNumbers = new HashSet<>(numbers);
        if (numbers.size() != noDuplicatedNumbers.size()) {
            throw new IllegalArgumentException(LOTTO_NUMBER_DUPLICATED_EXCEPTION);
        }
    }
}
