package lotto.domain;

import java.util.*;

import static lotto.domain.LottoRule.*;

public class Lotto {
    private Set<LottoNumber> lottoNumbers = new HashSet<>();

    public Lotto(List<Integer> numbers) {
        for (Integer number : numbers) {
            lottoNumbers.add(new LottoNumber(number));
        }

        if (lottoNumbers.size() != LOTTO_SIZE.get()) {
            throw new IllegalArgumentException("중복된 로또 번호가 존재합니다.");
        }
        if (lottoNumbers.size() < 6) {
            throw new IllegalArgumentException("로또 번호는 6개이어야 합니다.");
        }
    }

    public int calculateCountOfMatch(Lotto anotherLotto) {
        int countOfMatch = 0;
        for (LottoNumber lottoNumber : this.lottoNumbers) {
            countOfMatch += anotherLotto.lottoNumbers.contains(lottoNumber) ? 1 : 0;
        }
        return countOfMatch;
    }

    public List<String> convertStringList() {
        List<String> numbers = new ArrayList<>();
        for (LottoNumber lottoNumber : lottoNumbers) {
            numbers.add(lottoNumber.getNumber().toString());
        }
        return numbers;
    }
}
