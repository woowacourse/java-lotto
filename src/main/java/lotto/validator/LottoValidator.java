package lotto.validator;

import java.util.HashSet;
import java.util.List;
import lotto.domain.LottoNumber;
import lotto.exception.LottoException;

public class LottoValidator {

    private static final int LOTTO_SIZE = 6;

    public static void validate(List<LottoNumber> lottoNumbers) {
        checkSize(lottoNumbers);
        checkDuplicate(lottoNumbers);
    }

    private static void checkSize(List<LottoNumber> lottoNumbers) {
        if (!isCorrectSize(lottoNumbers)) {
            throw new LottoException(LottoException.WINNING_NUMBERS_SIZE_ERROR_MESSAGE);
        }
    }

    private static boolean isCorrectSize(List<LottoNumber> lottoNumbers) {
        return lottoNumbers.size() == LOTTO_SIZE;
    }

    private static void checkDuplicate(List<LottoNumber> lottoNumbers) {
        if (isDuplicate(lottoNumbers)) {
            throw new LottoException(LottoException.WINNING_NUMBERS_DUPLICATION_ERROR_MESSAGE);
        }
    }

    private static boolean isDuplicate(List<LottoNumber> lottoNumbers) {
        return new HashSet<>(lottoNumbers).size() != lottoNumbers.size();
    }
}
