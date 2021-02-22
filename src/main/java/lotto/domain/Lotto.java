package lotto.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Lotto {

    public static final String NUMBER_SIZE_ERROR = "[ERROR] 총 6개의 숫자를 입력해야 합니다.";
    public static final String DUPLICATE_ERROR = "[ERROR] 중복되는 숫자를 입력할 수 없습니다.";
    public static final int LOTTO_SIZE = 6;
    private final List<LottoNumber> lottoNumbers;

    public Lotto(List<LottoNumber> lottoNumbers) {
        validateSize(lottoNumbers);
        validateDuplicate(lottoNumbers);
        this.lottoNumbers = lottoNumbers;
    }

    private void validateSize(List<LottoNumber> lottoNumbers) {
        if (lottoNumbers.size() != LOTTO_SIZE) {
            throw new IllegalArgumentException(NUMBER_SIZE_ERROR);
        }
    }

    private void validateDuplicate(List<LottoNumber> lottoNumbers) {
        Set<LottoNumber> lottoNumberSizeCheck = new HashSet<>(lottoNumbers);
        if (lottoNumberSizeCheck.size() != lottoNumbers.size()) {
            throw new IllegalArgumentException(DUPLICATE_ERROR);
        }
    }

    public boolean isContain(LottoNumber lottoNumber) {
        return lottoNumbers.contains(lottoNumber);
    }

    public List<LottoNumber> getLottoNumbers() {
        return lottoNumbers;
    }

    @Override
    public String toString() {
        List<Integer> lottoNumbers = new ArrayList<>();
        for (LottoNumber lottoNumber : this.lottoNumbers) {
            lottoNumbers.add(lottoNumber.getNumber());
        }
        return lottoNumbers.toString();
    }
}
