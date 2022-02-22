package domain;

import java.util.Collections;
import java.util.List;

import static domain.LottoNumberConst.*;

public class LottoNumbers {

    private static final String LOTTO_NUMBER_SIZE_NOT_VALID = "로또 번호는 6자리여야 합니다.";
    private static final String LOTTO_NUMBER_DUPLICATE = "로또 번호는 중복되지 않아야 합니다.";

    private final List<LottoNumber> numbers;

    public LottoNumbers(List<LottoNumber> inputLottoNumbers) {
        validateSize(inputLottoNumbers);
        validateDuplicateLottoNumber(inputLottoNumbers);
        Collections.sort(inputLottoNumbers);
        numbers = inputLottoNumbers;
    }

    private void validateSize(List<LottoNumber> inputLottoNumbers) {
        if (inputLottoNumbers.size() != LOTTO_NUMBERS_SIZE) {
            throw new IllegalArgumentException(LOTTO_NUMBER_SIZE_NOT_VALID);
        }
    }

    private void validateDuplicateLottoNumber(List<LottoNumber> inputLottoNumbers) {
        long size = inputLottoNumbers.stream()
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
