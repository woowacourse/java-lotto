package lotto.domain;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Lotto {

    private Set<LottoNumber> lottoNumbers = new HashSet<>();

    public Lotto(List<Integer> numbers) {
        for (Integer number : numbers) {
            lottoNumbers.add(new LottoNumber(number));
        }

        if(lottoNumbers.size() != 6) {
            throw new IllegalArgumentException("중복된 로또 번호가 존재합니다.");
        }
    }
}
