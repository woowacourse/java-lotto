package domain.lottonumber;

import java.util.List;
import java.util.Objects;

public class LottoNumbers {
    private static final String ERROR_NULL_MESSAGE = "null값이 입력되었습니다.";
    private static final String ERROR_INVALID_SIZE_MESSAGE = "인자의 갯수가 올바르지 않습니다.";
    private static final String ERROR_DUPLICATE_MESSAGE = "중복된 인자가 존재합니다.";
    private static final int LOTTO_SIZE = 6;

    private List<LottoNumber> lottoNumbers;

    public LottoNumbers(List<LottoNumber> lottoNumbers) {
        validateNullValue(lottoNumbers);
        validateSize(lottoNumbers);
        validateDuplication(lottoNumbers);
        this.lottoNumbers = lottoNumbers;
        this.lottoNumbers.sort(null);
    }

    private void validateDuplication(List<LottoNumber> lottoNumbers) {
        if (lottoNumbers.stream().distinct().count() != LOTTO_SIZE) {
            throw new IllegalArgumentException(ERROR_DUPLICATE_MESSAGE);
        }
    }

    private void validateSize(List<LottoNumber> lottoNumbers) {
        if (lottoNumbers.size() != LOTTO_SIZE) {
            throw new IllegalArgumentException(ERROR_INVALID_SIZE_MESSAGE);
        }
    }

    private void validateNullValue(List<LottoNumber> lottoNumbers) {
        if (Objects.isNull(lottoNumbers)) {
            throw new IllegalArgumentException(ERROR_NULL_MESSAGE);
        }
    }

    public boolean contains(LottoNumber lottoNumber) {
        return lottoNumbers.contains(lottoNumber);
    }

    public int calculateMatchNumber(LottoNumbers comparedLottoNumbers) {
        return (int) lottoNumbers.stream()
                .filter(comparedLottoNumbers::contains)
                .count();
    }

    public List<LottoNumber> getLottoNumbers() {
        return lottoNumbers;
    }
}
