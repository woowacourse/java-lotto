package domain.lotto;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Lotto {
    private static final int MIN_LOTTO_VALUE = 1;
    private static final int MAX_LOTTO_VALUE = 45;
    private static final int LOTTO_NUMBERS_SIZE = 6;

    private final List<Integer> lottoNumbers;

    public Lotto(final List<Integer> lottoNumbers) {
        List<Integer> copy = new ArrayList<>(lottoNumbers);
        validateLottoNumbers(copy);
        this.lottoNumbers = copy;
    }

    private void validateLottoNumbers(final List<Integer> lottoNumbers) {
        validateDuplicate(lottoNumbers);
        validateNumber(lottoNumbers);
        validateLottoNumbersSize(lottoNumbers);
    }

    private void validateDuplicate(final List<Integer> lottoNumbers) {
        boolean isUnique = lottoNumbers.stream()
                .allMatch(new HashSet<>()::add);
        if (!isUnique) {
            throw new IllegalArgumentException("중복된 번호는 허용하지 않습니다.");
        }
    }

    private void validateNumber(final List<Integer> lottoNumbers) {
        boolean isBetweenNumber = lottoNumbers.stream()
                .allMatch(this::isBetweenNumber);
        if (!isBetweenNumber) {
            throw new IllegalArgumentException("1~45 사이의 번호만 허용합니다.");
        }
    }

    private void validateLottoNumbersSize(final List<Integer> lottoNumbers) {
        if (lottoNumbers.size() != LOTTO_NUMBERS_SIZE) {
            throw new IllegalArgumentException("6개의 로또 번호가 필요합니다.");
        }
    }

    private boolean isBetweenNumber(final int number) {
        return number >= MIN_LOTTO_VALUE && number <= MAX_LOTTO_VALUE;
    }
}

