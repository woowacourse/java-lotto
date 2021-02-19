package lotto.domain.ticketFactory;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lotto.domain.LottoNumber;
import lotto.exception.LottoCustomException;

public class FixedNumbersGenerator implements NumbersGenerator {

    public static final String NOT_DUPLICATE_NUMBERS_ERROR_MESSAGE = "당첨번호는 중복되지 않은 숫자들로 총 6개이어야 합니다.";
    private static final int EXACT_SIZE = 6;

    private final Set<Integer> fixedLottoNumbers;

    public FixedNumbersGenerator(List<Integer> fixedLottoNumbers) {
        this.fixedLottoNumbers = new HashSet<>(fixedLottoNumbers);
    }

    @Override
    public Set<LottoNumber> generateNumbers() {
        if (!isProperSize(fixedLottoNumbers)) {
            throw new LottoCustomException(NOT_DUPLICATE_NUMBERS_ERROR_MESSAGE);
        }
        Set<LottoNumber> lottoNumbers = new HashSet<>();
        fixedLottoNumbers.forEach(number -> lottoNumbers.add(new LottoNumber(number)));
        return lottoNumbers;
    }

    private boolean isProperSize(Set<Integer> lottoNumbers) {
        return lottoNumbers.size() == EXACT_SIZE;
    }
}
