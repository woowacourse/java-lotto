package lotto.domain;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Lotto {

    private final List<Integer> lottoNumbers;

    public Lotto(List<Integer> lottoNumbers) {
        this.lottoNumbers = lottoNumbers;
        validateNumberRange();
        validateDuplicateNumber();
    }

    private void validateNumberRange() {
        for(Integer number : lottoNumbers) {
            if(number < 1 || number >45) {
                throw new IllegalArgumentException("[ERROR] 범위를 벗어나는 숫자입니다.");
            }
        }
    }

    private void validateDuplicateNumber() {
        Set<Integer> set = new HashSet<>(lottoNumbers);
        if(set.size() != 6) {
            throw new IllegalArgumentException("[ERROR] 로또 번호는 중복될 수 없습니다.");
        }
    }

}
