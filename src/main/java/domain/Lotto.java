package domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Lotto {
    private static final int LENGTH = 6;

    private final List<Integer> lottoNumbers;

    public Lotto(List lottoNumbers) {
        validateLottoNumbers(lottoNumbers);
        this.lottoNumbers = new ArrayList(lottoNumbers);
    }

    private void validateLottoNumbers(List lottoNumbers) {
        validateDuplicatedLottoNumbers(lottoNumbers);
        validateLottoNumbersLength(lottoNumbers);
    }

    private void validateDuplicatedLottoNumbers(List lottoNumbers) {
        Set<Integer> distinctLottoNumbers = new HashSet(lottoNumbers);
        if (distinctLottoNumbers.size() != lottoNumbers.size()) {
            throw new IllegalArgumentException("로또 번호는 중복될 수 없습니다.");
        }
    }

    private void validateLottoNumbersLength(List lottoNumbers) {
        if (lottoNumbers.size() != LENGTH) {
            throw new IllegalArgumentException("로또 번호의 개수는 6자리입니다.");
        }
    }
}
