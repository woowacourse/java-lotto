package lotto.domain;

import java.util.*;

import static lotto.domain.LottoRule.*;

public class Lotto {
    private List<LottoNumber> lottoNumbers = new ArrayList<>();

    public Lotto(List<Integer> numbers) {
        if (numbers.size() != LOTTO_SIZE.get()) {
            throw new IllegalArgumentException("로또 번호는 6개이어야 합니다.");
        }
        for (Integer number : numbers) {
            lottoNumbers.add(LottoNumber.getInstance(number));
        }
        checkDuplication();
        Collections.sort(lottoNumbers);
    }

    void checkDuplication() {
        Set<Integer> checker = new HashSet<>();
        for (LottoNumber lottoNumber : lottoNumbers) {
            checker.add(lottoNumber.getNumber());
        }
        if (checker.size() != LOTTO_SIZE.get()) {
            throw new IllegalArgumentException("중복된 로또 번호가 존재합니다.");
        }
    }

    public int calculateCountOfMatch(Lotto anotherLotto) {
        int countOfMatch = 0;
        for (LottoNumber lottoNumber : this.lottoNumbers) {
            countOfMatch += anotherLotto.isContain(lottoNumber) ? 1 : 0;
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

    public boolean isContain(LottoNumber lottoNumber) {
        return lottoNumbers.contains(lottoNumber);
    }
}
