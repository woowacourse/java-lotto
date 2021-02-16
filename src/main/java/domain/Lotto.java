package domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Lotto {

    private final List<Integer> lottoNumbers;

    public Lotto(List lottoNumbers) {
        validateLottoNumbers(lottoNumbers);
        this.lottoNumbers = new ArrayList(lottoNumbers);
    }

    private void validateLottoNumbers(List lottoNumbers) {
        Set<Integer> distinctLottoNumbers = new HashSet(lottoNumbers);
        if (distinctLottoNumbers.size() != lottoNumbers.size()) {
            throw new IllegalArgumentException("로또 번호는 중복될 수 없습니다.");
        }
    }
}
