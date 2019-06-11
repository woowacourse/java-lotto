package lotto.domain;

import lotto.exception.LottoValidException;

import java.util.*;

public class Lotto {
    public static final int LOTTO_NUMBER_SIZE = 6;

    private List<LottoNumber> lottoNumbers = new ArrayList<>();

    public Lotto(List<Integer> lottoNumbers) {
        checkLottoSize(lottoNumbers);
        checkDuplicate(lottoNumbers);

        for (Integer number : lottoNumbers) {
            this.lottoNumbers.add(LottoNumber.valueOf(number));
        }
    }

    private void checkLottoSize(List<Integer> lotto) {
        if (lotto.size() != LOTTO_NUMBER_SIZE) {
            throw new LottoValidException("6개의 번호를 입력해주세요.");
        }
    }

    private void checkDuplicate(List<Integer> lotto) {
        Set set = new HashSet<>(lotto);
        if (lotto.size() != set.size()) {
            throw new LottoValidException("중복이 아닌 번호들로만 입력하세요.");
        }
    }

    boolean isContain(LottoNumber lottoNumber) {
        return lottoNumbers.contains(lottoNumber);
    }

    int matchNumber(Lotto lotto) {
        List<LottoNumber> matchLottoNumbers = new ArrayList<>(this.lottoNumbers);
        matchLottoNumbers.retainAll(lotto.lottoNumbers);
        return matchLottoNumbers.size();
    }

    @Override
    public String toString() {
        return lottoNumbers.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lotto lotto = (Lotto) o;
        return Objects.equals(lottoNumbers, lotto.lottoNumbers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lottoNumbers);
    }
}
