package lotto.domain;

import lotto.exception.LottoValidException;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;

public class Lotto {
    static final int LOTTO_NUMBER_SIZE = 6;
    private List<LottoNumber> lotto = new ArrayList<>();

    public Lotto(List<Integer> lotto) {
        checkLottoSize(lotto);
        checkDuplicate(lotto);
        for (Integer number : lotto) {
            this.lotto.add(LottoNumber.of(number.intValue()));
        }
    }

    private void checkLottoSize(List<Integer> lotto) {
        if (lotto.size() != LOTTO_NUMBER_SIZE) {
            throw new LottoValidException("6개의 번호를 입력해주세요.");
        }
    }

    private void checkDuplicate(List<Integer> lotto) {
        HashSet<Integer> set = new HashSet(lotto);
        if (lotto.size() != set.size()) {
            throw new LottoValidException("중복이 아닌 번호들로만 입력하세요.");
        }
    }

    boolean isContain(LottoNumber lottoNumber) {
        return lotto.contains(lottoNumber);
    }

    int matchNumber(Lotto lotto) {
        List<LottoNumber> matchLottoNumbers = new ArrayList<>(this.lotto);
        matchLottoNumbers.retainAll(lotto.lotto);
        return matchLottoNumbers.size();
    }

    @Override
    public String toString() {
        return "" + lotto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lotto lotto1 = (Lotto) o;
        return Objects.equals(lotto, lotto1.lotto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lotto);
    }

}
