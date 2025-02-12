package domain;

import exception.LottoException;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

public class LottoNumbers {
    private static final int LOTTO_VALID_SIZE = 6;
    private static final String INVALID_LOTTO_SIZE = "로또 번호는 6개여야 합니다.";
    private final List<LottoNumber> lottoNumbers;
    private final String DUPLICATE_LOTTO_NUMBERS = "로또 번호는 중복될 수 없습니다!";

    public LottoNumbers(List<Integer> lottoNumbers) {
        validateLottoNumbers(lottoNumbers);
        this.lottoNumbers = lottoNumbers.stream()
                .map(LottoNumber::new)
                .sorted()
                .toList();
    }

    private void validateLottoNumbers(List<Integer> lottoNumbers) {
        Set<Integer> duplicationSet = new HashSet<>(lottoNumbers);
        if(lottoNumbers.size() != duplicationSet.size()) {
            throw new LottoException(DUPLICATE_LOTTO_NUMBERS);
        }
        if(lottoNumbers.size() != LOTTO_VALID_SIZE){
            throw new LottoException(INVALID_LOTTO_SIZE);
        }
    }

}
