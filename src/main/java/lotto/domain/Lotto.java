package lotto.domain;

import java.util.*;

import static lotto.domain.LottoRule.*;

public class Lotto {
    private List<LottoNumber> lottoNumbers;

    public Lotto(List<LottoNumber> lottoNumbers) {
        if (lottoNumbers.size() != LOTTO_SIZE.get()) {
            throw new IllegalArgumentException("로또 번호는 6개이어야 합니다.");
        }

        this.lottoNumbers = new ArrayList<>(lottoNumbers);

        checkDuplication();
        Collections.sort(this.lottoNumbers);
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
            numbers.add(lottoNumber.toString());
        }
        return numbers;
    }

    public boolean isContain(LottoNumber lottoNumber) {
        return lottoNumbers.contains(lottoNumber);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");
        stringBuilder.append(String.join(", ", convertStringList()));
        stringBuilder.append("]");
        return stringBuilder.toString();
    }
}